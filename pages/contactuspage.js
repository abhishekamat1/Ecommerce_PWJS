// pages/ContactUsPage.js
const { expect } = require('@playwright/test');

class ContactUsPage {
    constructor(page) {
        this.page = page;
        this.contactUsLink = page.locator('a[href="/contact_us"]');
        this.getInTouchHeader = page.locator('//h2[normalize-space()="Get In Touch"]');
        this.nameInput = page.locator('input[placeholder="Name"]');
        this.emailInput = page.locator('input[placeholder="Email"]');
        this.subjectInput = page.locator('input[placeholder="Subject"]');
        this.messageInput = page.locator('#message');
        this.uploadFileInput = page.locator('input[name="upload_file"]');
        this.submitButton = page.locator('input[value="Submit"]');
        this.successMsg = page.locator('//div[@class="status alert alert-success"]');
    }

    async gotoHome() {
        await this.page.goto('http://automationexercise.com/');
        await expect(this.page).toHaveTitle('Automation Exercise');
    }

    async openContactForm() {
        await this.contactUsLink.click();
        await expect(this.getInTouchHeader).toBeVisible();
    }

    async fillContactForm(user) {
        await this.nameInput.fill(user.Name);
        await this.emailInput.fill(user.Email);
        await this.subjectInput.fill(user.Subject);
        await this.messageInput.fill(user.Message);

        if (user.FilePath) {
            await this.uploadFileInput.setInputFiles(user.FilePath);
        }
    }

    async submitForm() {
        // ✅ Attach dialog handler BEFORE clicking submit
        this.page.once('dialog', async dialog => {
            console.log(`Dialog message: ${dialog.message()}`);
            await dialog.accept();  // presses "OK"
        });

        await this.submitButton.click();
    }

    async assertSuccess() {
        await expect(this.successMsg).toBeVisible();
    }
}

module.exports = { ContactUsPage };

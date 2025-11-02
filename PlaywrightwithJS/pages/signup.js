// pages/SignupPage.js
const { expect } = require('@playwright/test');

class SignupPage {
  constructor(page) {
    this.page = page;
    this.loginLink = page.locator('a[href="/login"]');
    this.nameInput = page.locator('input[placeholder="Name"]');
    this.emailInput = page.locator('input[data-qa="signup-email"]');
    this.signupButton = page.locator('button[data-qa="signup-button"]');
    this.emailExistsMsg = page.locator('//p[normalize-space()="Email Address already exist!"]');

    // Registration form locators
    this.genderRadio = page.locator('#id_gender1');
    this.passwordInput = page.locator('#password');
    this.daySelect = page.locator('#days');
    this.monthSelect = page.locator('#months');
    this.yearSelect = page.locator('#years');
    this.newsletter = page.locator('//input[@id="newsletter"]');
    this.offer = page.locator('//input[@id="optin"]');
    this.firstName = page.locator('#first_name');
    this.lastName = page.locator('#last_name');
    this.company = page.locator('#company');
    this.address = page.locator('#address1');
    this.country = page.locator('#country');
    this.state = page.locator('#state');
    this.city = page.locator('#city');
    this.zip = page.locator('#zipcode');
    this.mobile = page.locator('#mobile_number');
    this.createAccountBtn = page.locator('button[data-qa="create-account"]');

    this.successMsg = page.locator('//p[contains(text(),"Congratulations! Your new account has been success")]');
    this.continueBtn = page.locator('//a[normalize-space()="Continue"]');

    // Post-signup locators
    this.loggedInAs = page.locator('//li[10]//a[1]');
    this.deleteLink = page.locator('a[href="/delete_account"]');
    this.deletedMsg = page.locator('//b[normalize-space()="Account Deleted!"]');
    this.continueAfterDeleteBtn = page.locator('//a[normalize-space()="Continue"]');
  }

  async navigateToLogin() {
    await this.page.goto('https://automationexercise.com');
    //await this.page.pause(9000);
    await this.loginLink.click();
  }

  async signupwithexistingEmail(name, email) {
    await this.nameInput.fill(name);
    await this.emailInput.fill(email);
    await this.signupButton.click();
  }

  async signupWithUniqueEmail(baseName, baseEmail, maxRetries = 20) {
    let suffix = 0;
    let emailExists = true;

    while (emailExists && suffix < maxRetries) {
      const currentName = suffix === 0 ? baseName : `${baseName}${suffix}`;
      const currentEmail = suffix === 0 ? baseEmail : baseEmail.replace('@', `${suffix}@`);

      await this.nameInput.fill(currentName);
      await this.emailInput.fill(currentEmail);
      await this.signupButton.click();

      emailExists = await this.emailExistsMsg.isVisible();
      if (emailExists) {
        suffix++;
        await this.page.goto('https://www.automationexercise.com/');
        await this.loginLink.click();
      } else {
        return { currentName, currentEmail };
      }
    }
    throw new Error('Could not sign up with a unique email after retries.');
  }

  async fillRegistrationForm(user) {
    await this.genderRadio.check();
    await this.passwordInput.fill(user.Password);
    await this.daySelect.selectOption(user.Day);
    await this.monthSelect.selectOption(user.Month);
    await this.yearSelect.selectOption(user.Year);
    await this.newsletter.click();
    await this.offer.click();
    await this.firstName.fill(user.FirstName);
    await this.lastName.fill(user.LastName);
    await this.company.fill(user.Company);
    await this.address.fill(user.Address);
    await this.country.selectOption(user.Country);
    await this.state.fill(user.State);
    await this.city.fill(user.City);
    await this.zip.fill(user.Zip);
    await this.mobile.fill(user.Mobile);

    await this.createAccountBtn.click();
  }

  async assertSignupSuccess() {
    await this.successMsg.waitFor({ state: 'visible' });
    await expect(this.successMsg).toContainText('Congratulations! Your new account has been successfully created!');
    await this.page.screenshot({ path: `../screenshots/signup-success-${Date.now()}.png` });

    // Continue after account creation
    await this.continueBtn.click();

    // New steps start here
    await expect(this.loggedInAs).toContainText('Logged in as '); // Verify "Logged in as <username>"
    await this.deleteLink.click(); // Click delete account
    await expect(this.deletedMsg).toBeVisible(); // Verify account deleted message
    await this.continueAfterDeleteBtn.click(); // Final Continue
  }
  async assertSignupFail() {
    await expect(this.emailExistsMsg).toBeVisible();
  }
}

module.exports = { SignupPage };

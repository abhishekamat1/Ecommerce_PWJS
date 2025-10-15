const { expect } = require('@playwright/test');

class HomePage {

    constructor(page) {
        this.page = page;
        this.productsLink = page.locator('a[href="/products"]');
        this.carousel = page.locator('(//div[@class="carousel-inner"])[1]');
        this.footer = page.locator('//footer[@id="footer"]');
        this.subscription = page.locator('div[class="single-widget"] h2');
        this.emailid = page.locator('//input[@id="susbscribe_email"]');
    }

    async gotoHome() {
        await this.page.goto('http://automationexercise.com/');
        await expect(this.carousel).toBeVisible();
    }

    async navigateToProducts() {
        await this.productsLink.click();
        await expect(this.page).toHaveURL('https://automationexercise.com/products');
    }

    async subscription() {
        //scroll down till footer
        await this.page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
        await expect(this.footer).toBeVisible();
        await expect(this.subscription).toBeVisible();


    }
};
module.exports = { HomePage };
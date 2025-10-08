const { expect } = require('@playwright/test');

class HomePage {

    constructor(page) {
        this.page = page;
        this.productsLink = page.locator('a[href="/products"]');
        this.carousel = page.locator('(//div[@class="carousel-inner"])[1]');
    }

    async gotoHome() {
        await this.page.goto('http://automationexercise.com/');
        await expect(this.carousel).toBeVisible();
    }

    async navigateToProducts() {
        await this.productsLink.click();
        await expect(this.page).toHaveURL('https://automationexercise.com/products');
    }
};
module.exports = { HomePage };
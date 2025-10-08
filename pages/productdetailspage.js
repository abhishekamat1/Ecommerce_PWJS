const { expect } = require('@playwright/test');
export class ProductDetailsPage {

    constructor(page) {
        this.page = page;
        this.productsContainer = page.locator('.features_items');
        this.firstProductLink = page.locator('a[href="/product_details/1"]');
        this.productName = page.locator('div[class="product-information"] h2');
        this.category = page.locator('//p[normalize-space()="Category: Women > Tops"]');
        this.price = page.locator('div[class="product-information"] span span');
        this.availability = page.getByText('Availability: In Stock');
        this.condition = page.getByText('Condition: New');
        this.brand = page.locator('//b[normalize-space()="Brand:"]');
    }

    async verifyProductsVisible() {
        await expect(this.productsContainer).toBeVisible();
    }

    async openFirstProduct() {
        await this.firstProductLink.click();
        await expect(this.page).toHaveURL('https://automationexercise.com/product_details/1');
    }

    async verifyProductDetails() {
        await expect(this.productName).toBeVisible();
        await expect(this.category).toBeVisible();
        await expect(this.price).toBeVisible();
        await expect(this.availability).toBeVisible();
        await expect(this.condition).toBeVisible();
        await expect(this.brand).toBeVisible();
    }
};
exports.module = { ProductDetailsPage };
const { expect } = require('@playwright/test');
export class ProductDetailsPage {

    constructor(page) {
        this.page = page;
        this.productsContainer = page.locator('.features_items');
        this.firstProductLink = page.locator('a[href="/product_details/1"]');
        this.productName = page.locator('div[class="product-information"] h2');
        this.category = page.locator('//div[@class="product-details"]//p[1]');
        this.price = page.locator('div[class="product-information"] span span');
        this.availability = page.locator('//div[@class="product-details"]//p[2]');
        this.condition = page.locator('//div[@class="product-details"]//p[3]');
        this.brand = page.locator('//div[@class="product-details"]//p[4]');
        this.search = page.locator('//input[@id="search_product"]');
        this.searchButton = page.locator('//button[@id="submit_search"]');
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

    async searchProduct(){
        await this.search.click();
        await this.search.fill('Tshirt');
        await this.searchButton.click();
        await expect(this.page).toHaveURL('https://automationexercise.com/products?search=Tshirt');
    }
};
exports.module = { ProductDetailsPage };
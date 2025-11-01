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
        this.productbutton = page.locator('//a[@href="/products"]');
        this.product1 = page.locator('(//div[@class="product-image-wrapper"])[1]');
        this.product2 = page.locator('(//div[@class="product-image-wrapper"])[2]');
        this.cartbutton1 = page.locator('(//a[contains(text(),"Add to cart")])[1]');
        this.cartbutton2 = page.locator('(//a[contains(text(),"Add to cart")])[3]');
        this.continuebutton = page.locator('//button[normalize-space()="Continue Shopping"]');
        this.viewcart = page.locator('a[href="/view_cart"]');
        this.addedproduct1 = page.locator('tr[id="product-1"] td[class="cart_description"]');
        this.addedproduct2 = page.locator('tr[id="product-2"] td[class="cart_description"]');
        this.productprice1 = page.locator('tr[id="product-1"] td[class="cart_price"] p');
        this.productprice2 = page.locator('tr[id="product-2"] td[class="cart_price"] p');
        this.productqty1 = page.locator('tr[id="product-1"] button[class="disabled"]');
        this.productqty2 = page.locator('tr[id="product-2"] button[class="disabled"]');
        this.proceedtocheckout = page.locator('.btn.btn-default.check_out');
        this.totalprice = page.locator('tbody tr td:nth-child(4) p:nth-child(1)');
        
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

    async searchProduct() {
        await this.search.click();
        await this.search.fill('Tshirt');
        await this.searchButton.click();
        await expect(this.page).toHaveURL('https://automationexercise.com/products?search=Tshirt');
    }

    async productpage() {
        await this.productbutton.click();
        await this.product1.click();
        await this.cartbutton1.click();
        await this.continuebutton.click();

        await this.product2.click();
        await this.cartbutton2.click();
        await this.continuebutton.click();
    }

    async viewproduct(){
        await this.viewcart.click();
        await this.addedproduct1.toBeVisible();
        await this.addedproduct2.toBeVisible();
        await this.productprice1.toBeVisible();
        await this.productprice2.toBeVisible();
        await this.productqty1.toBeVisible();
        await this.productqty2.toBeVisible();
        await this.proceedtocheckout.click();
        await this.totalprice.toBeVisible();

    }
};
exports.module = { ProductDetailsPage };
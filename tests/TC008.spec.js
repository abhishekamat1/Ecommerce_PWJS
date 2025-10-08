const { test } = require('@playwright/test');
const { HomePage } = require('../pages/homepage');
const { ProductDetailsPage } = require('../pages/productdetailspage');

test('Verify All Products and product detail page', async ({ page }) => {

    const homePage = new HomePage(page);
    const productDetails = new ProductDetailsPage(page);

    // Step 1: Go to home page
    await homePage.gotoHome();

    // Step 2: Navigate to products
    await homePage.navigateToProducts();

    // Step 3: Verify product listing
    await productDetails.verifyProductsVisible();

    // Step 4: Open first product
    await productDetails.openFirstProduct();

    // Step 5: Validate product details
    await productDetails.verifyProductDetails();
});

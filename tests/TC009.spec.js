const { test, expect } = require('@playwright/test');
const { HomePage } = require('../pages/homePage');
const { ProductDetailsPage } = require('../pages/productdetailspage');

test('Verify Search Products', async ({ page }) => {

    const homePage = new HomePage(page);
    const productDetails = new ProductDetailsPage(page);

    await homePage.gotoHome();
    await homePage.navigateToProducts();
    await productDetails.searchProduct();
    
    await page.locator('(//a[contains(text(),"View Product")])[2]').click();
    await expect(page.locator('div[class="product-information"] h2')).toBeVisible();

})
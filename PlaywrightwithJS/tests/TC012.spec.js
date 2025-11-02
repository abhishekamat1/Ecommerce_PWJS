const { test } = require('./authFixture');
const { expect } = require('@playwright/test');
const { ProductDetailsPage } = require('../pages/productdetailspage');
const { HomePage } = require('../pages/homepage');

test('Add products to cart after login', async ({ authenticatedPage }) => {
  const page = authenticatedPage;
  const homePage = new HomePage(page);
  const productDetails = new ProductDetailsPage(page);

  await homePage.gotoHome();
  await productDetails.productpage();
  await productDetails.viewproduct();
});

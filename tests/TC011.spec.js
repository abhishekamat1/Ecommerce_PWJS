//Verify Subscription in Cart page
const { test, expect } = require('@playwright/test');
const { HomePage } = require('../pages/homePage');

test('Verify Subscription in Cart page', async ({ page }) => {
  const homePage = new HomePage(page);

  await homePage.gotoHome();
  await homePage.cart();
  await homePage.verifySubscriptionSection();
  await homePage.subscribeWithEmail('testuser@example.com');
});
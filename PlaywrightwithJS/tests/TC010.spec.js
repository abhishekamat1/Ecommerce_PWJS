// tests/TC_Subscription.spec.js
const { test, expect } = require('@playwright/test');
const { HomePage } = require('../pages/homePage');

test('Verify Subscription functionality on home page', async ({ page }) => {
  const homePage = new HomePage(page);

  await homePage.gotoHome();
  await homePage.verifySubscriptionSection();
  await homePage.subscribeWithEmail('testuser@example.com');
});

const { test, expect } = require('@playwright/test');
const { HomePage } = require('../pages/homepage');

test('Verify Subscription', async ({ page }) => {

    const homePage = new HomePage(page);

    await homePage.gotoHome();
    await homePage.subscription();


});
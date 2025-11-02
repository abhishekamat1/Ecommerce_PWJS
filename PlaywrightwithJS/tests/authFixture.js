// tests/authFixture.js
const { test: base } = require('@playwright/test');
const { LoginPage } = require('../pages/loginpage');
const { HomePage } = require('../pages/homepage');
const { readExcelData } = require('../pages/readexcel');

const filePath = '/Users/abhishekamat/Documents/Ecom_PWJS/testdata/testdata.xlsx';

// ✅ Read login credentials (only successful ones)
const [validUser] = readExcelData(filePath, 'login').filter(row => row.ExpectedResult === 'Success');

const test = base.extend({
  authenticatedPage: async ({ page }, use) => {
    const homePage = new HomePage(page);
    const loginPage = new LoginPage(page);

    await homePage.gotoHome();
    await loginPage.navigateToLogin();
    await loginPage.login(validUser.Username, validUser.Password);
    await loginPage.assertLoginSuccess(validUser.Username.split('@')[0]);

    await use(page);
  },
});

module.exports = { test };

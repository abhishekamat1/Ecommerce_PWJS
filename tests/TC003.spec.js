// InvalidLogin.spec.js

import { test, expect } from '@playwright/test';
const { LoginPage } = require('../pages/loginpage');
const { readExcelData } = require('../pages/readexcel');

const filePath = '/Users/abhishekamat/Documents/Ecomm_PWJS/testdata/testdata.xlsx';
// Read login data from "Login" sheet
const loginData = readExcelData(filePath, 'login');

test.describe('Invalid Login Tests', () => {
  for (const row of loginData) {
    if (row.ExpectedResult === 'Fail') {
      test(`Login with invalid credentials [${row.TestCase}]`, async ({ page }) => {
        const loginPage = new LoginPage(page);

        // Navigate
        await loginPage.gotoHome();
        await loginPage.navigateToLogin();

        // Attempt login
        await loginPage.login(row.Username, row.Password);

        // Verify error
        await loginPage.assertLoginFail();

        console.log(`Invalid login attempt for: ${row.Username}`);
      });
    }
  }
});

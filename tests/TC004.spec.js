import { test } from '@playwright/test';
const { LoginPage } = require('../pages/loginpage');
const { readExcelData } = require('../pages/readexcel');

const filePath = '/Users/abhishekamat/Documents/Ecomm_PWJS/testdata/testdata.xlsx';
const loginData = readExcelData(filePath, 'login');

test.describe('Logout Flow', () => {
    for (const row of loginData) {
        if (row.ExpectedResult === 'Success') {
            test(`logout with valid credentials [${row.TestCase}]`, async ({ page }) => {
                const loginPage = new LoginPage(page);

                await loginPage.gotoHome();
                await loginPage.navigateToLogin();

                //Enter email and password and login
                await loginPage.login(row.Username, row.Password);

                //Logout
                await loginPage.logout();

                console.log(`Successfully logged in and out for: ${row.Username}`);
            });
        }
    }
});

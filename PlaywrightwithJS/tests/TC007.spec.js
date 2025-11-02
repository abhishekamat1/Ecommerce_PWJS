import { expect, test } from '@playwright/test';

test('Verify test case page', async ({ page }) => {

    await page.goto('http://automationexercise.com');
    await expect(page.locator('(//div[@class="carousel-inner"])[1]')).toBeVisible();
    await page.locator('//a[contains(text(),"Test Cases")]').click();
    await expect(page.locator('div[class="panel-group"] h5 span')).toBeVisible();

})
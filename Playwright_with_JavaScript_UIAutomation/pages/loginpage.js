// pages/LoginPage.js
import { expect } from "@playwright/test";

class LoginPage {
  constructor(page) {
    this.page = page;
    this.signupLoginLink = page.locator('text=Signup / Login');
    this.loginHeader = page.locator('//h2[normalize-space()="Login to your account"]');
    this.emailInput = page.locator('input[data-qa="login-email"]');
    this.passwordInput = page.locator('input[placeholder="Password"]');
    this.loginButton = page.locator('button[data-qa="login-button"]');
    this.loggedInText = page.locator('//li[10]//a[1]'); // "Logged in as ..."
    this.errorMsg = page.locator('//p[normalize-space()="Your email or password is incorrect!"]');
    this.logoutButton = page.locator('a[href="/logout"]');

  }

  async gotoHome() {
    await this.page.goto('https://www.automationexercise.com');
    await expect(this.page).toHaveTitle('Automation Exercise');
  }

  async navigateToLogin() {
    await this.signupLoginLink.click();
    await expect(this.loginHeader).toBeVisible();
  }

  async login(email, password) {
    await this.emailInput.fill(email || ""); // handle empty case
    await this.passwordInput.fill(password || "");
    await this.loginButton.click();
  }
  async assertLoginSuccess(expectedUsername) {
    // Get text, trim spaces, normalize case
    const text = (await this.loggedInText.textContent()).trim().toLowerCase();
    const expected = `logged in as ${expectedUsername}`.toLowerCase();
    expect(text).toBe(expected);
  }

  async assertLoginFail() {
    await expect(this.errorMsg).toBeVisible();
  }

  async logout() {
  await this.logoutButton.click();
}

}

module.exports = { LoginPage };
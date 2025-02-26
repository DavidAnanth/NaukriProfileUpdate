Feature: To validate the Naukri website functionalities
As a User
I want to Validate the functionalities of Naukri website
so that I can got assurance against the Naukri.

Scenario: To valide the User profile update functionality
Given Open chrome browser and hit the Naukri url
When when website is loaded, click on Login button.
Then provide the valid Username in the username field
And provide the valid password in the password field
Then Click on Login button
When Profile page is loaded click on View Profile button
Then click on update resume button and upload the updated resume


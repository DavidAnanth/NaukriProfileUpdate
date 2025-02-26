package org.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
glue={"org.stepdefinition","Hooks"},
monochrome = true,
dryRun = false,
plugin = "pretty")
public class TestRunner {

}

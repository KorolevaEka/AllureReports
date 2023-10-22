import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class TabIssueTests {
    private static final String REPOSITORY = "junit-team/junit5";
    private static final String URL = "https://github.com/";
    private static final String LOC = ".search-input-container";
    private static final String SEARCH = "#query-builder-test";
    private static final String ISSUES = "CleanupMode.ON_SUCCESS doesn't work";

    @BeforeEach
    void enableAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Searching in repo")
    @Story("Searching in repo junit5")
    @Owner("KateK")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "JUnit5", url = "https://github.com/junit-team/junit5")
    @DisplayName("Check Issue in repo junit5 with Listener")
    public void checkNameIssue() {
        open(URL);
        $(LOC).click();
        $(SEARCH).setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUES)).should(Condition.visible);
    }

    @Test
    @Feature("Searching in repo")
    @Story("Searching in repo junit5")
    @Owner("KateK")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "JUnit5", url = "https://github.com/junit-team/junit5")
    @DisplayName("Check Issue in repo " + REPOSITORY + " with lambda steps")
    public void checkNameIssueLambda() {
        step("Open main page", () -> {
            open(URL);
        });
        step("Search repo" + REPOSITORY, () -> {
            $(LOC).click();
            $(SEARCH).setValue(REPOSITORY).pressEnter();
        });
        step("Open repo " + REPOSITORY , () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Open Issues", () -> {
            $("#issues-tab").click();
        });
        step("Check Issues on page: " + ISSUES, () -> {
            $(withText(ISSUES)).should(Condition.exist);
        });
    }

    @Test
    @Feature("Searching in repo")
    @Story("Searching in repo junit5")
    @Owner("KateK")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "JUnit5", url = "https://github.com/junit-team/junit5")
    @DisplayName("Check Issue in repo junit5 with annotation @Step")
    public void checkNameIssueWithAnnotatedStep() {
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchRepository(REPOSITORY, LOC, SEARCH);
        steps.openRepository(REPOSITORY);
        steps.openTabIssues();
        steps.checkNameIssues(ISSUES);
    }

}
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {

    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com");
    }


    @Step("Search repo {repo}")
    public void searchRepository(String repo, String loc, String search) {
        $(loc).click();
        $(search).setValue(repo).pressEnter();
    }

    @Step("Open repo {repo}")
    public void openRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Open Issues")
    public void openTabIssues() {
        $("#issues-tab").click();
    }

    @Step("Check Issues on page: {issue}")
    public void checkNameIssues(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}
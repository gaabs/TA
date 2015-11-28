package pages.StudentPages

import geb.Page

class StudentPage extends Page {

    static url = "/TA/student/index"

    static at =  {
        title ==~ /Student List/
    }

    def choose(String login){
        String id = "#"+login+"Compare"
        $(id).click()
    }

}

package ta

import commom.SheetImporter
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class SheetController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sheet.list(params), model: [conceptInstanceCount: Sheet.count()]
    }

    def show(Sheet conceptInstance) {
        respond conceptInstance
    }

    def create() {
        respond new Sheet(params)
    }

    @Transactional
    def save(Sheet conceptInstance) {
        if (conceptInstance == null) {
            notFound()
            return
        }

        if (conceptInstance.hasErrors()) {
            respond conceptInstance.errors, view: 'create'
            return
        }

        conceptInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'concept.label', default: 'Sheet'), conceptInstance.id])
                redirect conceptInstance
            }
            '*' { respond conceptInstance, [status: CREATED] }
        }
    }

    def edit(Sheet conceptInstance) {
        respond conceptInstance
    }

    @Transactional
    def update(Sheet conceptInstance) {
        if (conceptInstance == null) {
            notFound()
            return
        }

        if (conceptInstance.hasErrors()) {
            respond conceptInstance.errors, view: 'edit'
            return
        }

        conceptInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Sheet.label', default: 'Sheet'), conceptInstance.id])
                redirect conceptInstance
            }
            '*' { respond conceptInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Sheet conceptInstance) {

        if (conceptInstance == null) {
            notFound()
            return
        }

        conceptInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Sheet.label', default: 'Sheet'), conceptInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Sheet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def upload(){

    }

    def submit() {
        def f = request.getFile('datafile')

        String filename = params.datafile.getOriginalFilename()

        if (f.empty){
            flash.error = 'file cannot be empty'
            render(view: 'upload')
            return
        }

        File newFile = new File(filename)
        f.transferTo(newFile)
        uploadSheet(filename)
        newFile.delete()
    }

    def uploadSheet(String filename){

        SheetImporter sheetImporter
        try {
            sheetImporter = new SheetImporter(filename)
            def validColumns = sheetImporter.hasValidColumns()

            if (!validColumns){
                flash.error = "Error! \nFirst column should be named 'aluno', \nsecond column should be named 'login' \nand the third column should have a header"
            } else {
                def name, login, criterion, concept
                List<Map> data = sheetImporter.getConcepts()

                criterion = sheetImporter.getCriterion()

                createCriterion(criterion)

                def cont = new StudentController()

                for (Map m : data) {
                    name = m.get('aluno')
                    login = m.get('login')
                    concept = m.get(criterion)

                    createStudent(name,login, criterion)
                    println "tentando adicioanr o conceito '${concept}' ao critério '${criterion}' do aluno '${login}'"
                    cont.updateConcepts(login, criterion, concept)
                }

                flash.message = 'Sheet uploaded!'

            }

        } catch(IllegalArgumentException e) {
            flash.error = "Invalid file format!"
        }

        render view: "upload"
    }

    private void createCriterion(String criterion) {
        boolean criterionExists = EvaluationCriterion.findByName(criterion) != null

        def cont
        if (!criterionExists) {
            cont = new EvaluationCriterionController()
            cont.params << [name: criterion]
            cont.saveEvaluationCriterion(cont.createEvaluationCriterion())
        }
    }

    private void createStudent(String name, String login, String criterion) {
        boolean studentExists = Student.findByLogin(login) != null

        if (!studentExists) {
            println "criou estudante " + login + " " + name

            def cont = new StudentController()
            cont.params << [login: login] << [name: name]
            cont.saveStudent(cont.createStudent())
        }
    }

}

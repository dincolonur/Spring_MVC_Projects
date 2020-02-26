Phone Book Spring MVC Project:

    To Test the project;
        - Run embedded Jetty server with command "mvn jetty:run"
        - Then enter http://localhost:8080/phonebook-mvc address which will open index.ftl(Main Page)
        - Main page has 2 links 'Upload Files' and 'Get User List (PDF)'
        - Upload Files page please select /input/users.txt, /input/phones.txt, /input/companies.txt files
        - Get User List (PDF) link will list the Users in PDF file.

     1 - Project Batch loading can be done by Upload Files Pages. Uploading files will insert files data into
     users, phone_numbers and phone_companies tables which are created in embedded Derby Database.

     2 - PDFController is responsible for listing of users as a PDF document.

     3 - PDFController and UploadController are responsible for UI.
        * index.ftl is main page,
        * uploadMulti.ftl is for files upload
        * uploadStatus.ftl is for upload status report (it has also link to Get User List Page)
        * exceptionPage.ftl is for handling all exceptions

     4 - Generic exception handler implemented in GlobalExceptionHandler class.

      All pages implemented using Freemarker.

      PhoneBookConfig and MyWebInitializer are used for configuring Spring MVC application context and dispatcher servlet.


OD

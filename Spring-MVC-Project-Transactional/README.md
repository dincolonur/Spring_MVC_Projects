Phone Book Spring MVC Project:

    To Test the project;
        - Run embedded Jetty server with command "mvn jetty:run"
        - Then enter http://localhost:8080/phonebook-mvc address which will open index.ftl(Main Page)
        - Main page has 2 links 'Upload Files' and 'Get User List (PDF)'
        - Upload Files page please select /input/users.txt, /input/phones.txt, /input/companies.txt files
        - Get User List (PDF) link will list the Users in PDF file.

Version - 1:

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

Version - 2:

    1 - Spring Security structure is added to the project.

    2 - password and roles fields added to User entity. Roles: REGISTERED_USER(normal user), BOOKING_MANAGER(super user)
    REGISTERED_USER can only see own information and can not upload files. BOOKING_MANAGER sees all users information and can uplaod files.

    For Testing purpose, while application  starts 2 users information automatically inserted to DB.
    Username: Karl  password: 12345 roles: REGISTERED_USER
    Username: John  password: 33124 roles: REGISTERED_USER,BOOKING_MANAGER

    John can see as PDF all users information but Karl can only see his own and can not upload file.

    3 - Authentication information is obtained from Embedded Derby Database users table. DAOAuthenticationProvider and custom UserDetailsService is used.

    4 - Remember-Me authentication configured.

    5 - Password encoding during authentication is implemented.

Version - 3:

    1 - UserAccount entity added. Also in DB new user_accounts table added.
    Primary data is inserted to table while application startup.
    
    2 - changeMobileOperator method added. changeMobileOperator method makes possible for admin to change user's phone operator by 
    changing Phone Company ID. And It also updates the phone number data of User. Phone number table's PhoneCompany field is updated.
    If User does not have enough amount on the related User account to change the phone company(Change cost is new field on phone_companies table)
    operation will not be completed.
    
    3 - PlatformTransactionManager Bean added to PhoneBookConfig Configuration class.
    
    4 - changeMobileOperator method become Transactional. In UserAccountImpl, updatePhoneCompany method is called by changeMobileOperator method.
    In updatePhoneCompany by using @Transactional usage, method has become transactional.
    
    To Test this new feature;
        - Login as admin  -> Username: John  password: 33124
        - Upload files under input folder
        - In uploadStatus page, there is a new link -> Click to User Account Operations
        - In new page(account.jsp), Choose User Account from the list by pressing the Edit link and update the account by using the form.
        - Only Phone Company ID text field can be edited and updated.
        - If you get any exception, rollback will work.

OD

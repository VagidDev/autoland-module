module com.college.controller {
    requires com.college.model;
    requires jakarta.persistence;
    requires java.xml;

    exports com.college.controller;
    exports com.college.controller.validators.user;
}
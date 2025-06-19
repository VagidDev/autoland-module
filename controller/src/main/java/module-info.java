module com.college.controller {
    requires com.college.model;
    requires jakarta.persistence;
    requires java.xml;
    requires java.desktop;

    exports com.college.controller;
    exports com.college.controller.validators.user;
}
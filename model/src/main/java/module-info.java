module com.college.model {
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;

    exports com.college.model.entity;
    exports com.college.model.database.session;
    exports com.college.model.database.interfaces;
    exports com.college.model.database.exceptions;
    exports com.college.model.keys;

    uses java.sql.Driver;

    opens com.college.model.entity to org.hibernate.orm.core;
}
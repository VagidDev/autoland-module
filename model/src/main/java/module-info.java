module com.college.model {
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.desktop;
    requires static commons.collections;

    exports com.college.model.entity;
    exports com.college.model.database.session;
    exports com.college.model.database.interfaces;
    exports com.college.model.database.exceptions;
    exports com.college.model.entity.keys;

    uses java.sql.Driver;

    opens com.college.model.entity to org.hibernate.orm.core;
    opens com.college.model.entity.keys to org.hibernate.orm.core;
}
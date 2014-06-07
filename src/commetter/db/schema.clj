(ns commetter.db.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".h2.db"))))

(defn create-comments-table
  []
  (sql/db-do-commands
    db-spec
    (sql/create-table-ddl
      :comments
      [:id "varchar(20) PRIMARY KEY AUTO_INCREMENT"]
      [:name "varchar(30)"]
      [:comment "varchar(200)"]
      [:timestamp :timestamp]))
   (sql/db-do-prepared db-spec
     "CREATE INDEX timestamp_insdex ON comments (timestamp)"))

(defn drop-comments-table
  []
  (sql/db-do-commands
    db-spec
    (sql/drop-table-ddl
      :comments)))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-comments-table))

(defn drop-tables
  []
  (drop-comments-table))

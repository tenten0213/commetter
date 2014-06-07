(ns commetter.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [commetter.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity comments)

(defn save-comment [name comment]
  (insert comments
          (values
           {:name name
            :comment comment
            :timestamp (new java.util.Date)})))

;(defn update-user [id first-name last-name email]
;  (update users
;  (set-fields {:first_name first-name
;               :last_name last-name
;               :email email})
;  (where {:id id})))

(defn get-comments []
  (select comments))

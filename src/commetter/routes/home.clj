(ns commetter.routes.home
  (:use compojure.core)
  (:require [commetter.layout :as layout]
            [commetter.util :as util]
            [commetter.db.core :as db]))

(defn home-page [& [name comment error]]
  (layout/render
    "home.html"
     {:error error
      :name name
      :comment comment
      :comments (db/get-comments)}))

(defn save-comment [name comment]
  (cond
   (empty? name)
   (home-page name comment "Somebody forgot to leave a name")

   (empty? comment)
   (home-page name comment "Don't you have something to say?")

   :else
   (do
     (db/save-comment name comment)
     (home-page))))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/" [name comment] (save-comment name comment))
  (GET "/about" [] (about-page)))

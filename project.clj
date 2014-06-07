(defproject
  commetter
  "0.1.0-SNAPSHOT"
  :repl-options
  {:init-ns commetter.repl}
  :dependencies
  [[ring-server "0.3.1"]
   [com.h2database/h2 "1.3.175"]
   [environ "0.5.0"]
   [com.taoensso/timbre "3.2.1"]
   [markdown-clj "0.9.44"]
   [org.clojure/clojure "1.6.0"]
   [com.taoensso/tower "2.0.2"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [korma "0.3.1"]
   [selmer "0.6.6"]
   [lib-noir "0.8.3"]]
  :ring
  {:handler commetter.handler/app,
   :init commetter.handler/init,
   :destroy commetter.handler/destroy}
  :profiles
  {:uberjar {:aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.2"]],
    :env {:dev true}}}
  :url
  "http://example.com/FIXME"
  :plugins
  [[lein-ring "0.8.10"] [lein-environ "0.5.0"]]
  :description
  "FIXME: write description"
  :min-lein-version "2.0.0")

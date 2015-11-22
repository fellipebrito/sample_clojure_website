(ns site.core
  (:require [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]])) ; httpkit is a server

(defroutes myapp
  (GET "/" [] "Hello World")
  (GET "/hello/:name" [name] (str "Hello " name)))

(defn -main []
  (run-server myapp {:port 5000}))

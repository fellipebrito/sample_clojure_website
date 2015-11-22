(ns site.core
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all]
            [org.httpkit.server :refer [run-server]]
            [selmer.parser :refer [render-file]])) ; httpkit is a server

(defroutes myapp
  (GET "/" [] "Hello World")
  (GET "/hello/:name" [name]
           (render-file "templates/hello.html" {:name name}))

  (GET "/posts" req
    (let [title (get (:params req) :title)
          author (get (:params req) :author)]
      (str "Title: " title ", Author: " author))))

(defn -main []
  (run-server (wrap-defaults myapp site-defaults) {:port 5000}))

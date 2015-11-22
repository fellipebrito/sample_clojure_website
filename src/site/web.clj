(ns site.web
  (:require [compojure.core :refer :all];; routing library
            [ring.middleware.defaults :refer :all];; HTTP server abstraction
            [org.httpkit.server :refer [run-server]] ; httpkit is a server
            [selmer.parser :refer [render-file]] ;; templates
            [markdown.core :as md];; markdown
            [clojure.java.io :as io]))

(defn parse-metadata [file]
  (md/md-to-html-string-with-meta (-> file io/resource slurp)))

(defn markdown [req]
  {:status 200 
   :headers {"Content-Type" "text/html"}
   :body    (render-file "templates/markdown.html"
                         (parse-metadata "templates/markdown.md"))})

(defroutes app
  (GET "/" [] "Hello World")
  (GET "/hello/:name" [name]
           (render-file "templates/hello.html" {:name name}))

  (GET "/markdown" [] markdown)

  (GET "/posts" req
    (let [title (get (:params req) :title)
          author (get (:params req) :author)]
      (str "Title: " title ", Author: " author))))

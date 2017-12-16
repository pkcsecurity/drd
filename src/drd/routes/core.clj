(ns drd.routes.core
  (:require [ring.middleware.json :as json]
            [ring.middleware.file :as file]
            [ring.middleware.content-type :as ct]
            [drd.roles.core :as roles]
            [drd.utils.core :as u]
            [compojure.core :as r]
            [compojure.route :as route]
            [hiccup.core :as html]))

(def index
  (html/html {:mode :html}
             [:head
              [:meta {:charset "utf-8"}]
              [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
              [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
              [:title "drd"]]
             [:body 
              [:div#app]
              [:script {:src (if (= "DEV" @u/mode) "development/index.js" "release/index.js")}]]))

(r/defroutes routes
  (r/GET "/" [] 
         (constantly 
           {:status 200
            :headers {"Content-Type" "text/html"}
            :body index}))
  (route/not-found nil))

(def app
  (-> routes
      (json/wrap-json-response)
      (json/wrap-json-body {:keywords? true})
      (roles/wrap-security)
      (file/wrap-file "static" {:index-files? false})
      (ct/wrap-content-type)))

(ns guestbook.routes.home
  (:require
   [guestbook.layout :as layout]
   [guestbook.messages :as msg]
   [guestbook.middleware :as middleware]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render
   request
   "home.html"))

(defn message-list [_]
  (response/ok (msg/message-list)))

(defn save-message! [{:keys [params]}]
  (try
    (msg/save-message! params)
    (response/ok {:status :ok})
    (catch Exception e
      (let [{id :guestbook/error-id
             errors :errors} (ex-data e)]
        (case id
          :validation
          (response/bad-request {:errors errors})
          ;; else
          (response/internal-server-error
           {:errors {:server-error ["Failed to save message!"]}}))))))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/messages" {:get message-list}]
   ["/message" {:post save-message!}]
   ["/about" {:get about-page}]])


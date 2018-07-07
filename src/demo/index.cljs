(ns ^{:hoplon/page "index.html"} demo.index
  (:require
   [com.rpl.specter :as sp]
   [hoplon.core :as h]
   [javelin.core :as j]
   [hoplon.jquery]
   [hoplon.ui  :as u]
   [hoplon.ui.attrs :as a]
   )
   )

(enable-console-print!)

(j/defc seed [1])

(defn specter-trans [v]
  (sp/transform [sp/ALL] inc v))

(j/defc= transformula
  (specter-trans seed))

(j/cell=
 (do
   (.clear js/console)
   (prn seed)
   (prn transformula)))



(h/html
  (h/head
    (h/link :href "app.css" :rel "stylesheet" :type "text/css"))
  (h/body
   (h/h1 "Hello, Hoplon!")
   (u/elem :p 20 :gv 10

     (u/elem :sh (a/r 1 1)
             :click #(swap! seed conj 1)
             "seed: "  seed))

   (u/elem :sh (a/r 1 1)
           "transformula: " transformula)

   (u/elem :sh (a/r 1 1)
           "transformula in another formula: " (j/cell= (conj transformula 100)))

   ))

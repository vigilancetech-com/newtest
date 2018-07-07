(set-env!
 :dependencies '[[com.rpl/specter "1.1.0"]
                 [adzerk/boot-cljs-repl     "0.3.0"      :scope "test"]
                 [weasel                  "0.7.0"  :scope "test"]
                 [com.cemerick/piggieback "0.2.1"  :scope "test"]
                 [org.clojure/tools.nrepl "0.2.12" :scope "test"]
;
                 [adzerk/boot-cljs          "1.7.228-2"]
                 [adzerk/boot-reload        "0.4.13"]
                 [hoplon/hoplon             "6.0.0-alpha17"]
                 [org.clojure/clojure       "1.8.0"]
                 [hoplon/ui                 "0.3.0-SNAPSHOT"]

                 [org.clojure/clojurescript "1.9.293"]
                 [tailrecursion/boot-jetty  "0.1.3"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[com.rpl.specter :refer [select setval transform]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl ]]
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build address-book for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs-repl)
    (cljs)
    (serve :port 8000)))

(deftask prod
  "Build address-book for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (target :dir #{"target"})))

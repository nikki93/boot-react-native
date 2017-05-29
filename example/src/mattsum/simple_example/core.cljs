(ns mattsum.simple-example.core
  (:require [reagent.core :as r]
            [cljs.test :as test]))


(enable-console-print!)


(set! js/React (js/require "react"))
(defonce ReactNative (js/require "react-native"))
(defonce expo (js/require "expo"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def Alert (.-Alert ReactNative))

(defn alert [title]
  (.alert Alert title))

(defn app-root []
  [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
   [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} "hi"]
   [touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                         :on-press #(alert "HELLO!")}
    [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]])

(defn root-container
  "Wraps root-view. This is to make sure live reloading using boot-reload and
  reagent works as expected. Instead of editing root-container, edit root-view"
  []
  [app-root])

(defn ^:export main
  []
  (js/console.log "MAIN")
  (enable-console-print!)
  (.registerComponent app-registry "main" #(r/reactify-component root-container)))

(defn on-js-reload
  []
  (r/render #'root-container 1))


(comment

  (println "omg")

  )

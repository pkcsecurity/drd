(ns drd.startup.core
  (:require [drd.startup.properties :as p]))

(defn startup []
  "Runs before server start time to prepare the application for runtime."
  (p/push-configs))

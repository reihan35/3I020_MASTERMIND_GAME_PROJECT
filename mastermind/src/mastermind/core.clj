(ns mastermind.core
  (:gen-class))

(declare lancer-jeu)
(declare code-secret)
(declare indications)
(declare comparer)
(declare demande-couleur)

(def pionsC [:rouge :bleu :vert :jaune :noir :blanc])




(defn -main [& args]
  (println "Bienvenue au MasterMind, voulez-vous commencer une partie ? 1:Oui 2:Non")
  (let [response (read-line)] ;;read-line récupère un string sur la ligne de commande
    (if (= (compare response "1") 0)
      (lancer-jeu)
      (println "Dommage, à bientôt !"))))



(defn lancer-jeu []
  (println "C'est bien, commençons ! Allez-vous devenir le mastermind ultime ?")
  (let [codeSecret (code-secret 4)
        tentative 2]
    (println "Le maître a fait son choix parmi les couleurs suivantes :rouge :bleu :vert :jaune :noir :blanc, il y a 4 pions à deviner")
    (loop [m 0
           res []]
      (if (< m tentative)
        if(= (demande-couleur res) false))






(defn demande-couleur []
  (println "Quelle est votre première couleur")
  (conj res (read-line))
  (println res)
  (println "Quelle est votre deuxieme couleur ?")
  (conj res (read-line))
  (println "Quelle est votre troisieme couleur ?")
  (conj res (read-line))
  (println "Quelle est votre derniere couleur ?")
  (conj res (read-line))
  (println res)
  (let [resp (indications codeSecret res)]
    (if (some #(= (compare :bad %) 0) resp)
      false
      true)))





(defn code-secret [n]
  (loop [m 0
         res []]
    (if (< m n)
      (recur (inc m) (conj res (rand-nth pionsC)))
      res)))



(defn comparer [cs ts v]
  (if (= (compare cs ts) 0)
    :good
    (if (some #(= (compare ts %) 0) v)
      :color
      :bad)))

(defn indications [v1 v2]
  (loop [i 0, res []]
    (if (< i (count v1)) ;;On parcourt le vecteur
      (recur (inc i) (conj res (comparer (get v1 i) (get v2 i) v1)))
      res)))

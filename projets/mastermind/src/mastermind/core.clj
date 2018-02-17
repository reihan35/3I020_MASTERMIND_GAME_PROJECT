(ns mastermind.core
  (:gen-class))

(declare lancer-jeu)
(declare code-secret)
(declare comparer)
(declare indications)
(declare lireLine)
(declare filtre-indications)
(declare freqs-dispo)
(declare frequences)
(declare compter)
(declare filtrer)
(declare convertirKey)
(declare verifColor)
(declare restart)

(def pionsC [:red :blue :green :yellow :black :white])




(defn -main [& args]
  (println "Welcome to the MatserMind ! Do you want to start a new game ?")
  (println "1:yes,why not?")
  (println "2:WTH! not at all")
  (let [response (read-line)] ;;read-line récupère un string sur la ligne de commande
    (if (= (compare response "1") 0)
      ;;On lance le jeu si la reponse est 1
      (lancer-jeu)
      (println "Too bad ! see you later ..."))))


;;Fonction permettant de gerer le jeu : lancement et arrêt du jeu
(defn lancer-jeu []
  (println "Perfect! Will you be the next mastermind?")
  ;;On initialise le code secret ainsi que le nombre de tentatives
  (let [codeSecret (code-secret 4) tentative 12]
    (println "The Boss has chosen 4 colors from red,blue,green,yellow,black and white so let's start !")
    (loop [m 0]
      ;;m correspond au nombre de tentatives réalisées
      (println "You have" (- tentative m) "more tries")
      (if (< m tentative)
        (if(= (verifColor (lireLine) codeSecret) true) ;;Permet de vérifier si le vecteur tapé correspond au code secret
          (do (println "OMG ! Congratulations! I can't believe it ! You won ! Another game ? ")
            (println "1:it was fun, so yes")
            (println "2:I need a break, so no thanks")
            (restart))
          (do (println "Too bad ! let's retry") (recur (+ m 1)))) ;;refait une tentative si mauvais vecteur tapé
        (println "Too bad, you used up all your tries !")))))




;;fonction qui permet de traiter la relance du jeu
(defn restart []
  (let [res (read-line)]
    (if (= (compare res "1") 0)
      (lancer-jeu)
      (println "Too bad, it was fun ! See you later !"))))



;;Fonction qui permet de lire les valeurs taper par l'utilisateur sur le terminal
(defn lireLine []
  (loop [tentative 0
          res []]
          (if (< tentative 4)
          	(let [lire (read-line)]
          		;;We need to check if there is only one argument written in the input
          		(if (some #(= (compare (keyword lire) %) 0) pionsC) ;;TODO on ne peut pas compter le nombre d'argumets, il faudra donc vérifier l'argument est dans pionsC
          			;;If there is only one input, can ask the next argument
          			(recur (inc tentative) (conj res lire))
          			(do (println "Wrong arguments ! Please enter only one argument with the correct format : example red") (recur tentative res))))
          	(do (println res) (convertirKey res)))))



;;Cette fonction va permettre de transformer les elements dans le vecteur tapé par le joueur et le transforme en mot clé, puisque les éléments
;;tapés sont des strings
(defn convertirKey [v]
	(loop [cpt 0
		res []]
		(if (< cpt (count v))
			(recur (inc cpt) (conj res (keyword (get v cpt))))
			res)))



(defn verifColor [codePlayer codesecret]
  (let [resp (filtre-indications codesecret codePlayer (indications codesecret codePlayer))]
    (println resp)
    (if (some #(not= (compare :good %) 0) resp)
      false
      true)))





;;****************************************************************************************


;;Cette fonction permet de construire un code secret à n tokens
(defn code-secret [n]
  (loop [m 0
         res []]
    (if (< m n)
      (recur (inc m) (conj res (rand-nth pionsC)))
      res)))


;;****************************************************************************************


;;These two methods return an indication about the current game

;;Cette fonction permet de déterminer le contenu de l'indication à rendre
(defn comparer [cs ts v]
  (if (= (compare cs ts) 0)
    :good
    (if (some #(= (compare ts %) 0) v)
      :color
      :bad)))

;;Cette fonction permet de donner des indications sur l'avancement du jeu
;;v1 est le vecteur code secret, tandis que v2 est le vecteur tapé par l'utilisateur
(defn indications [v1 v2]
  (loop [i 0
        res []]
    (if (< i (count v1)) ;;On parcourt le vecteur
      (recur (inc i) (conj res (comparer (get v1 i) (get v2 i) v1)))
      res)))


;;****************************************************************************************
;;On supprime tous les va dans le vecteur v puisqu'on a déjà calculer son occurence
(defn filtrer [va v]
  (filter #(not= va %) v))

;;Compter le nombre de va dans le vecteur v
(defn compter [va v]
  (loop [cpt 0
         tv v
         res {}]
    (if (seq tv)
      (if (= (compare va (first tv)) 0)
        (recur (inc cpt) (rest tv) (assoc res va (inc cpt)))
        (recur cpt (rest tv) res))
      res)))

;;Cette fonctions permet de calculer l'occurence d'une clé dans un vecteur v
(defn frequences [v]
  (loop [tv v
         res {}] ;;Parcourt le vector
    (if (seq tv) ;;vérification si c'est vide
      (recur (filtrer (first tv) tv) (conj res (compter (first tv) tv)))
      res)))


;;****************************************************************************************
;;Cette fonction permet de déterminer combien de pions de couleur il reste à deviner
(defn freqs-dispo [v1 v2]
  (loop [m 0
         res (frequences v1)]
    (if (< m (count v1))
      (if (= (compare (get v2 m) :good) 0)
        (recur (inc m) (assoc res (get v1 m) (dec (get res (get v1 m)))))
        (recur (inc m) res))
      res)))



;;****************************************************************************************
;;Le deuxième paramètre ne contient pas des mots clés, il faut donc les congreenir avec la fonction name pour transformer un mot clé en string
(defn filtre-indications [v1 v2 v3]
  (loop [i 0
         res []
         cpt (freqs-dispo v1 v3)]
    (if (< i (count v1))
      (if (= (compare (get v3 i) :color) 0) ;;Si c'est un color
        (if (= 0 (get cpt (get v2 i))) ;;On vérifie si la cardinalité est bonne
          (recur (inc i) (assoc res i :bad) cpt) ;;Si pas bonne cardinalité, on change à :bad
          (recur (inc i) (conj res (get v3 i)) (assoc cpt (get v2 i) (dec (get cpt (get v2 i))))))
        (recur (inc i) (conj res (get v3 i)) cpt))
      res)))


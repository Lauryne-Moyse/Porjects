#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jun  1 17:07:36 2021

@author: swendy
"""
import numpy as np
import random as rd

###MODELISATION : METHODE DE CONDORCET###


###GENERATION D'UN SUFFRAGE###

#Génère un classement aléatoire des candidats 
def gen_listeC(n):
    L=[]
    while len(L)<n:
        cand=rd.randint(0,n-1)
        if cand not in L:
           L.append(cand)
    return L

#Pour n candidats, il y a n! classements possibles 
def factorielle(n):
    if n==0:
        return 1
    else:
        return n*factorielle(n-1)

def gen_suffrageC(n):
    card=rd.randint(2,factorielle(n))  #Choix aléatoire du nb de
    suffrage=[]                        #classements distincts
    while len(suffrage)<card:
        liste=gen_listeC(n)            #Liste de listes regroupant
        if liste not in suffrage:      #tous les classements
           suffrage.append(liste)
    for i in suffrage:               #Ajout du nombre de votants
        i.append(rd.randint(1,100))  #pour ce classement
    return suffrage                  

#Normalisation pour que la somme des votants fasse 100  
def gen_suff_norm(n):
    suffrage=gen_suffrageC(n)
    sum=0
    for i in suffrage:
        sum+=i[n]
    for i in suffrage:
        i[n]=i[n]*100/sum
    return suffrage 



###REALISATION D'UNE METHODE DE CONDORCET###

#Comptabilise les points des candidats dans un duel 
def duelC(suffrage,i,j,n):
    voix_i=0
    voix_j=0
    for k in suffrage:             #Balayage de tous les
        if k.index(i)<k.index(j):  #classements        
                voix_i+=k[n]       #Récupération du pourcentage  
        else :                     #affecté à un classement    
                voix_j+=k[n]
    return (voix_i,voix_j)


#Retourne le vainqueur d'un duel
def vainqueurC(suffrage,i,j,n): 
    paire=duelC(suffrage,i,j,n)
    if paire[0]>paire[1]:
        return i 
    elif paire[0]<paire[1]:
        return j 
    

#Détermine le vainqueur de Condorcet (s'il existe)
def vainqueur_Condorcet(n):
    suffrage=gen_suff_norm(n)
    for i in range(n):
        winner=i
        adversaires=[k for k in range(n)] #Duel de i avec les autres
        adversaires.remove(i)             #candidats excepté lui 
        for j in adversaires:
            v=vainqueurC(suffrage,i,j,n)
            if v!=i:
               winner='PARADOXE'
               break
        if winner==i:             #On vérifie si i est vainqueur
           return winner          #de Condorcet ou non
    return winner
        
#Avec ce code, si un candidat a une égalité dans un duel, il ne peut 
#pas être élu vainqueur 
#Si le vainqueur de Condorcet existe, alors il est unique 





###CALCUL DE PROBABILITE###

def proba(n):
    count=0
    for i in range(100):
        win=vainqueur_Condorcet(n)
        if win=='PARADOXE':
           count+=1
    return(count)

def proba_tot(n):    
    sum=0
    for i in range(10):
        count=proba(n)
        sum+=count
    return sum/10






##############################################################



###MODELISATION :JUGEMENT MAJORITAIRE ET SUMDT###

#Différents modèles régissant la répartition des chiffre de 0 à 6 
#attribués à chaque candidat 
#Répartition alpha/beta : int(rd.betavariate(4-(0.3-0.1*i)*i,1+1.3*i)*6.99)
#int(rd.betavariate(4-0.3*i,3+0.3*i)*6.99)
#Répartition uniforme : int(rd.uniform(0,7))
#Clivage : int(rd.betavariate(0.1*(i+1),0.1*(i+1))*6.99)


#Dictionaire des mentions attribuables 
D={'0':'très bien', '1':'bien', '2':'assez bien', '3':'passable',
   '4':'assez mauvais','5':'mauvais','6':'à rejeter'}



###GENERATION D'UN SUFFRAGE###

#Génère le suffrage d'un votant 
def gen_liste(n):
    L=np.zeros((1,n))
    for i in range(n):
        valeur=int(rd.uniform(0,7))
        L[0][i]=valeur 
    return L




#Génère un suffrage de n votants
def gen_suffrage(n=7,card=1000):                                  
    suffrage=np.zeros((card,n))               
    for i in range(card):
        liste=gen_liste(n)  
        suffrage[i]=liste
    return suffrage            



###RECUPERATION DES RESULTATS###

#Compte pour chaque candidat le nombre de mentions reçues
def resultat(suffrage):
    _,n=np.shape(suffrage)
    resultats=np.zeros((n,7))
    for i in range(n):
        result_cand=list(suffrage[:,i])
        for j in range(7):
            resultats[i][j]=result_cand.count(j)
    return resultats 


#Détermination de la mention majoritaire de chaque candidat
def mention(suffrage):
    card,n=np.shape(suffrage)
    result=resultat(suffrage)
    mediane=int(card/2)
    mentions=[]
    for j in range(n):
        count=0
        i=0
        while count<mediane:
            count+=result[j][i]
            i+=1
        mentions.append(i-1)   
    return result,mentions



###DETERMINATION DES GAGNANTS###
 
#Gagnant jugement majoritaire
def gagnant_JM(suffrage):
    _,n=np.shape(suffrage)
    result,mentions=mention(suffrage)
    mention_gagnante=min(mentions)
    mentions_litt=[]                   
    for i in mentions:
        x=str(i)              #Equivalent chiffre/mention
        mentions_litt.append(D[x])    
    print(mentions_litt)                      
    favoris=[]
    for i in range(n):
        if mentions[i]==mention_gagnante:
            favoris.append(i)
    vainqueur=[favoris[0],D[str(mention_gagnante)]]
    m=len(favoris)
    if m==1:
        return vainqueur
    else:
        a_rejeter=list(result[:,6])             #Départage des fav
        liste_favoris=[a_rejeter[i] for i in favoris] #Choix du 
        mini=min(liste_favoris)                 #candidat avec le
        vainqueur[0]=a_rejeter.index(mini)      #moins de à rejeter
        return vainqueur 


       
#Gagnant suffrage uninominal majoritaire à deux tours 
def gagnant_SUMDT(suffrage):   
    suff_madt=[]
    n,m=np.shape(suffrage)
    for i in range(n):              #On détermine à qui le votant
        liste=list(suffrage[i,:])   #a attribué la meilleure mention 
        mini=min(liste)             #parmi celles attribuées
        egalite=[]
        for k,l in enumerate(liste):  
            if l==mini:                      #Liste des candidats ayant  
               egalite.append(k)             #cette mention et choix
        suff_madt.append(rd.choice(egalite)) #d'un gagnant parmi eux 
                                                   
    nb_voix=[]
    for i in range(m):                   #L'obtention d'une 
     nb_voix.append(suff_madt.count(i))  #mention max donne
    print(nb_voix)                       #une voix au candidat
    
    #Détermination des deux vaiqnueurs du premier tour              
    deuxieme_tour=[]                         
    maxi=max(nb_voix)                       
    deuxieme_tour.append(nb_voix.index(maxi)) 
    bis=[i for i in nb_voix if i!=maxi]
    maxi_2=max(bis)
    deuxieme_tour.append(nb_voix.index(maxi_2))
    print(deuxieme_tour)
    
    #Détermination du vainqueur du deuxième tour 
    gagnant_1,gagnant_2=deuxieme_tour[0], deuxieme_tour[1]      
    nb_voix=[0,0]                       #On garde le même suffrage
    for i in range(n):                  #Un candidat obtient la voix
           vote=list(suffrage[i,:])     #d'un votant s'il lui a
           if vote[gagnant_1]<vote[gagnant_2]: #donné une meilleure 
               nb_voix[0]+=1                   #mention qu'à l'autre
           elif vote[gagnant_1]>vote[gagnant_2]:
               nb_voix[1]+=1
           else:
               i=rd.randint(0,1)
               nb_voix[i]+=1
    print(nb_voix)
    if nb_voix[0]>=nb_voix[1]:
        return gagnant_1
    else:
        return gagnant_2

            
def gagnants(suffrage):
    return suffrage,(gagnant_JM(suffrage),gagnant_SUMDT(suffrage))




####EVALUATION DE LA STABILITE#### 
    
#Simulation du retrait d'un candidat de l'élection 
def changement(suffrage,i):
    suffrage_bis=np.delete(suffrage,i,axis=1)
    _,win=gagnants(suffrage_bis)
    gagnant_JM,gagnant_SUMDT=win
    if gagnant_JM[0]>=i:               #La suppression d'une colonne
        gagnant_JM[0]+=1               #change les indices 
    if gagnant_SUMDT>=i:   
        gagnant_SUMDT+=1
    return (gagnant_JM,gagnant_SUMDT)




    


###RESPECT DU PRINCIPE DE CONDORCET###

#Comptabilise les points des candidats dans un duel 
def duel(suffrage,i,j):
    voix_i=0
    voix_j=0
    m,n=np.shape(suffrage)
    for k in range(m):                    #Balayage de tous les
        if suffrage[k,i]<suffrage[k,j]:   #classements        
                voix_i+=1                 
        elif suffrage[k,i]>suffrage[k,j]:                                
                voix_j+=1
        else:
            voix=rd.choice([voix_i,voix_j])
            voix+=1
    return (voix_i,voix_j)




#Détermination du vainqueur d'un duel
def vainqueur(suffrage,i,j): 
    paire=duel(suffrage,i,j)
    if paire[0]>paire[1]:
        return i 
    elif paire[0]<paire[1]:
        return j 
    

def gagnant_C(suffrage):
    _,n=np.shape(suffrage)
    winner=0
    for i in range(n):
        winner=i
        bis=[k for k in range(n)] #Duel de i avec tous les autres
        bis.remove(i)             #candidats excepté lui 
        for j in bis:
            v=vainqueur(suffrage,i,j)
            if v!=i:
               winner='PARADOXE' 
               break
        if winner==i:                            #On vérifie si i
           return (gagnant_JM(suffrage),winner)  #est vainqueur de 
    return (gagnant_JM(suffrage),winner)         #Condorcet 


def gagnant_C2(suffrage):
    _,n=np.shape(suffrage)
    winner=0
    for i in range(n):
        winner=i
        bis=[k for k in range(n)] 
        bis.remove(i)            
        for j in bis:
            v=vainqueur(suffrage,i,j)
            if v!=i:
               winner='PARADOXE' 
               break
        if winner==i:                           
           return (gagnant_SUMDT(suffrage),winner)    
    return (gagnant_SUMDT(suffrage),winner)
 
#Comparaison de l'identité des gagnants avec deux méthodes
def count(): 
    count=0
    paradoxe=0
    for i in range(100):
        win=gagnant_C(gen_suffrage())
        if win[0][0]==win[1]:
           count+=1
        if win[1]=='PARADOXE':
           paradoxe+=1
    return count, paradoxe







###COMPARAISON DES TEMPS D'EXECUTION###
    
import time as tm
import matplotlib.pyplot as plt


#SUMDT non réalisé à partir d'un jugement majoritaire 
def gen_suffrage_SU(n,card):
    suffrage=[]
    for i in range(card):
        suffrage.append(int(rd.betavariate(4-0.3*2,3+0.3*2)*n))
    return suffrage


def gagnant_SU(suffrage): 
    n=max(suffrage)+1
    resultat=[]
    for i in range(n):
        points=suffrage.count(i)
        resultat.append(points)
    print(resultat)
    return suffrage,resultat.index(max(resultat))
        
        
def temps_JM(suffrage):
    t1=tm.clock()
    gagnant_JM(suffrage)
    t2=tm.clock()
    temps=t2-t1
    return temps
def temps_SU(suffrage):
    t1=tm.clock()
    gagnant_SU(suffrage)
    t2=tm.clock()
    temps=t2-t1
    return temps

"""
n=10
Y=[1000,10000,100000,500000,1000000]
X1=[temps_JM(gen_suffrage(n,card)) for card in Y]
X2=[temps_SU(gen_suffrage_SU(n,card)) for card in Y]
plt.plot(Y,X1,'o',label='Jugement majoritaire')
plt.plot(Y,X2,'o',label='Suffrage majoritaire à un tour')
plt.legend()
"""
    
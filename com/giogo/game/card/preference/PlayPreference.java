/*
 * PlayPreference.java
 *
 * Created on July 11, 2003, 4:27 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Game;
import com.giogo.game.card.PlayCards;
import com.giogo.game.card.CardGame;
import com.giogo.game.card.CardPlayer;
import com.giogo.game.card.Deck;
import com.giogo.game.card.Card;
import com.giogo.game.card.Suit;
import com.giogo.game.card.TricksUpdate;
import com.giogo.game.card.BiddingCardGame;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.PlayersIterator;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author  inexum
 */
public class PlayPreference extends PlayCards
{
    /** Creates a new instance of PlayPreference */
    public PlayPreference() 
    {
        super(10);
    }

    protected void handEnd(int round, CardGame game) 
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "hand [" + round + "]");
        super.handEnd(round, game);
        // resolve tricks
        PreferencePlayer prevRoundWinner = (PreferencePlayer)game.roundWinner();
        Iterator at;
        if(prevRoundWinner == null)
            at = new PlayersIterator(game.getPlayers(), game.firstPlayer().id());
        else
            at = new PlayersIterator(game.getPlayers(), prevRoundWinner.id());
        Deck trick = null;
        CardPlayer  winner = null;
        Card        winningCard = null;
        while(at.hasNext())
        {
            CardPlayer player = (CardPlayer)at.next();
            Deck playerThrow = player.giveThrownCards();
            if(playerThrow == null) continue;
            if(trick == null)
            {
                trick = playerThrow;
                winner = player;
                winningCard = playerThrow.getTopCard();
            }
            else
            {
                Card playerCard = playerThrow.getTopCard();
                Suit playerCardSuit = playerCard.getSuit();
                Suit winningCardSuit = winningCard.getSuit();
                if(winningCardSuit.compareTo(playerCardSuit) == 0)
                {
                    if(winningCard.compareTo(playerCard) < 0)
                    {
                        winner = player;
                        winningCard = playerCard;
                    }
                }
                else if(isTrump((BiddingCardGame)game, playerCardSuit))
                {
                    winner = player;
                    winningCard = playerCard;
                }
                trick.add(playerCard);
            }
        }
        if(winner != null)
        {
            winner.addTrick(trick);
            game.roundWinner(winner);
            game.receive(new TricksUpdate(game, winner));
        }
    }
    
    private boolean isTrump(BiddingCardGame game, Suit suit)
    {
        PreferenceBid bid = (PreferenceBid)game.biddingWinner().bid();
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out("bid = " + bid.name() + ", ordinal= "
                                        + bid.ordinal());
        return(bid.isTrump(suit));
    }

    protected boolean isEnd(CardGame game) 
    {
        BiddingCardGame biddingGame = (BiddingCardGame)game;
        PreferencePlayer leadPlayer = (PreferencePlayer)biddingGame.biddingWinner();
        PreferenceBid bid = (PreferenceBid)leadPlayer.bid();
        if(bid.isBettel() || bid.isSix()) 
            return(game.roundWinner() == leadPlayer);
        Iterator at = game.getPlayers().iterator();
        int tricksNo = 0;
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            if(player == leadPlayer)
                continue;
            tricksNo += player.tricksNo();
        }
        return(tricksNo > 4);
    }

    protected String[] activeCardPresenters(CardGame game, CardPlayer player)
    {
        String[] allPresenters = super.activeCardPresenters(game, player);
        List cardHolders = new ArrayList();
        Deck playerHand = player.currentHand();
        int handSize = playerHand.size();
        
        PreferencePlayer firstPlayer = firstPlayer(game);
        
        if(player != firstPlayer)
        {
            try
            {
                enableSameSuit((Preference)game, playerHand, firstPlayer, 
                                    cardHolders, allPresenters, handSize);
                if(cardHolders.size() == 0)
                   enableTrumps((Preference)game, playerHand, cardHolders, 
                                            allPresenters, handSize);
                if(cardHolders.size() == 0)
                    enableHand(cardHolders, allPresenters, handSize);
            }
            catch(NullPointerException e)
            {
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out(e);
                enableHand(cardHolders, allPresenters, handSize);
            }
                
        }
        else
        {
            enableHand(cardHolders, allPresenters, handSize);
        }
        return((String[])cardHolders.toArray(new String[1]));
    }
    
    private void enableHand(List cardHolders, String[] allPresenters, int handSize)
    {
        for(int at = 0; at < handSize; at++)
            cardHolders.add(allPresenters[at]);
    }
    
    private void enableTrumps(Preference pref, Deck playerHand, List cardHolders, 
                                            String[] allPresenters, int handSize)
    {
        PreferenceBid playBid = (PreferenceBid)pref.biddingWinner().bid();        
        for(int at = 0; at < handSize; at++)
        {
            Suit cardSuit = playerHand.cardAt(at).getSuit();
            if(playBid.isTrump(cardSuit))
                cardHolders.add(allPresenters[at]);
        }
    }
    
    private void enableSameSuit(Preference pref, Deck playerHand, 
                                        PreferencePlayer firstPlayer,
                                        List cardHolders, 
                                        String[] allPresenters, int handSize)
    {
        Deck thrownCards = firstPlayer.thrownCards();
        Card thrownCard = thrownCards.getTopCard();
        Suit thrownSuit = thrownCard.getSuit();
        for(int at = 0; at < handSize; at++)
        {
            Suit cardSuit = playerHand.cardAt(at).getSuit();
            if(cardSuit.equals(thrownSuit))
                cardHolders.add(allPresenters[at]);
        }
    }
    
    private PreferencePlayer firstPlayer(CardGame game)
    {
        PreferencePlayer player = (PreferencePlayer)game.roundWinner();
        if(player == null)
            player = (PreferencePlayer)game.firstPlayer();
        if(!player.isPlaying())
        {
            List players = game.getPlayers();
            int nextPlayerId = (player.id() + 1) % players.size();
            player = (PreferencePlayer)players.get(nextPlayerId);
        }
        return(player);
    }
}

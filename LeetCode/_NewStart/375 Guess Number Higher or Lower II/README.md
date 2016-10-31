﻿<p><p>We are playing the Guess Game. The game is as follows:<p> 

<p>I pick a number from <strong>1</strong> to <strong>n</strong>. You have to guess which number I picked.</p>

<p>Every time you guess wrong, I'll tell you whether the number I picked is higher or lower. </p>

<p>However, when you guess a particular number x,  and you guess wrong, you pay <b>$x</b>. You win the game when you guess the number I picked.</p>

<p>
<b>Example:</b>
<pre>
n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
</pre>
</p>

<p>Given a particular <strong>n &ge; 1</strong>, find out how much money you need to have to guarantee a <b>win</b>.</p>

<ol id="hints">
   <li class="hint">The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the <b>first</b> scenario.</li>
  <li class="hint"> Take a small example (n = 3). What do you end up paying in the worst case?</li>
 <li class="hint"> Check out <a href="https://en.wikipedia.org/wiki/Minimax">this article</a> if you're still stuck.</li>
 <li class="hint">The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming. </li>
 <li class="hint">As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss? </li>
 </ol>
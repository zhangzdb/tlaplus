--algorithm Dijkstra1
  procedure Foo(a = 1) 
   variable x = 42 ; y = x 
   begin Foo1: return ;
   end procedure
  process P \in 1..3
    begin 
    P1: assert x = y ;
        skip ; 
    P2: call Foo(17) ;
    end process;

  end algorithm

----------- MODULE bug_05_12_16b -----------
EXTENDS Naturals, Sequences, TLC
 
\* BEGIN TRANSLATION
VARIABLES pc, stack, a, x, y

vars == << pc, stack, a, x, y >>

ProcSet == (1..3)

Init == (* Procedure Foo *)
        /\ a = [ self \in ProcSet |-> 1]
        /\ x = [ self \in ProcSet |-> 42]
        /\ y = [ self \in ProcSet |-> x[self]]
        /\ stack = [self \in ProcSet |-> << >>]
        /\ pc = [self \in ProcSet |-> "P1"]

Foo1(self) == /\ pc[self] = "Foo1"
              /\ pc' = [pc EXCEPT ![self] = Head(stack[self]).pc]
              /\ x' = [x EXCEPT ![self] = Head(stack[self]).x]
              /\ y' = [y EXCEPT ![self] = Head(stack[self]).y]
              /\ a' = [a EXCEPT ![self] = Head(stack[self]).a]
              /\ stack' = [stack EXCEPT ![self] = Tail(stack[self])]

Foo(self) == Foo1(self)

P1(self) == /\ pc[self] = "P1"
            /\ Assert(x[self] = y[self], 
                      "Failure of assertion at line 8, column 9.")
            /\ TRUE
            /\ pc' = [pc EXCEPT ![self] = "P2"]
            /\ UNCHANGED << stack, a, x, y >>

P2(self) == /\ pc[self] = "P2"
            /\ /\ a' = [a EXCEPT ![self] = 17]
               /\ stack' = [stack EXCEPT ![self] = << [ procedure |->  "Foo",
                                                        pc        |->  "Done",
                                                        x         |->  x[self],
                                                        y         |->  y[self],
                                                        a         |->  a[self] ] >>
                                                    \o stack[self]]
            /\ x' = [x EXCEPT ![self] = 42]
            /\ y' = [y EXCEPT ![self] = x'[self]]
            /\ pc' = [pc EXCEPT ![self] = "Foo1"]

P(self) == P1(self) \/ P2(self)

Next == (\E self \in ProcSet: Foo(self))
           \/ (\E self \in 1..3: P(self))
           \/ (* Disjunct to prevent deadlock on termination *)
              ((\A self \in ProcSet: pc[self] = "Done") /\ UNCHANGED vars)

Spec == Init /\ [][Next]_vars

Termination == <>(\A self \in ProcSet: pc[self] = "Done")

\* END TRANSLATION

========================================



package CodeGen;

public class Code {
    private char code [];
    private int N[];
    private int n;

    public Code()
    {
        n = 1;
        code[0] = ' ';
        N[0] = 0;
    }

    public Code(int m)
    {
        n = m;
        code = new char[n];
        N = new int[n];
    }
    
    public Code(int m, int [] M)
    {
        n = m;
        for (int i=0; i<n; i++)
            N[i] = M[i];
    }

    public void runRandom()
    {
        for (int i=0; i<n; i++)
        {
            if (i%6 == 0)
                N[i] = (int) Math.round(Math.random()*128);
            if (i%6 == 1)
            {
                N[i] = (int)
                    Math.round
                    (Math.IEEEremainder(Math.random()*128, Math.random()*128));
                if (isNegative(N[i]))
                    N[i] = -N[i];
            }
            if (i%6 == 2)
                N[i] = ((int) (N[i-2]*(Math.E * Math.PI))) % 128;
            if (i%6 == 3)
            {
                N[i] = ((int) (Math.round(Math.exp(N[i-3])))) % 128;
                if (isNegative(N[i]))
                    N[i] = -N[i];
                if (N[i] == 1)
                {
                    N[i] = (int)
                        Math.round(Math.random()*Math.scalb(N[1],N[0]*128));
                    if (isNegative(N[i]))
                        N[i] = -N[i];
                }
            }
            if (i%6 == 4)
                N[i] = ((int) (Math.log1p(N[i-1])*255)) % 128;

            if (i%6 == 5)
                N[i] = this.hashCode() % 128;
        }
    }
    
    public boolean isNegative(int a)
    {
        if (a<0)
            return true;
        else
            return false;
    }

    public void CodeGenerator()
    {
        runRandom();

        //alternativi za nekoi znaci
        for (int i =0; i<n; i++)
        {
            //alternativi za ASCII znacite vo intervalot [0,32]
            if ( (N[i] >= 0) && (N[i] <= 32) )
                N[i] = N[i] + 33;

            //alternativa za znakot "
            if (N[i] == 34)
                N[i] = 102;

            //alternativi za znacite ' , ) i (
            if ((N[i] >= 39) && (N[i] <= 41) )
                N[i] = N[i] - 4;

            //alternativi za znacite +, ,, -, ., /
            if ((N[i] >= 43) && (N[i] <= 47))
                N[i] = N[i] + 7;

            //alternativa za znacite :, ;, <, =, >, ?
            if ((N[i] >= 58) && (N[i] <=63))
            {
                N[i] = N[i] + 36;
                if (N[i] == 96) //alternativa za znakot `
                    N[i] = 35;
            }

            //zamena na znacite [, ], \
            if ((N[i] >= 91) && (N[i] <= 93))
                N[i] = N[i] - 10;

            //alternativa za znakot `
            if (N[i] == 96)
                    N[i] = 35;
            //zamena za znacite {, |, }, ~, DEL
            if ((N[i] >= 123) && (N[i] <= 127))
            {
                if (N[i] == 123)
                    N[i] = 35;
                if (N[i] == 124)
                    N[i] = 94;
                if (N[i] == 125)
                    N[i] = 37;
                if (N[i] == 126)
                    N[i] = 95;
                if (N[i] == 127)
                    N[i] = 38;
            }
        }

        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    //bez mali bukvi (gi pretvora vo golemi)
    public void NoSmallLetters()
    {
        //resetiranje na malite bukvi vo golemi
        for (int i=0; i<n; i++)
            if ((N[i] >= 97) && (N[i] <= 122))
                N[i] = N[i] - 32;
        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    //bez golemi bukvi (gi pretvora vo mali)
    public void NoCapitalLetters()
    {
        //pretvoranje na golemite bukvi vo mali bukvi
        for (int i=0; i<n; i++)
            if ((N[i] >= 65) && (N[i] <= 90))
                N[i] = N[i] + 32;

        //pretvoranje vo kod
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    // bez specialni znaci (gi pretvora vo bukvi i cifri)
    public void NoCharacters()
    {
        //gi pretvora karakterite vo bukvi i cifri
        for (int i=0; i<n; i++)
        {
            if (N[i] == 33)
                N[i] = 73;
            if ((N[i] >= 35) && (N[i] <= 38))
                N[i] = N[i] + 13;
            if (N[i] == 42)
                N[i] = 109;
            if (N[i] == 64)
                N[i] = 112;
            if (N[i] == 94)
                N[i] = 87;
            if (N[i] == 95)
                N[i] = 89;
        }

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    //bez cifri (gi pretvora vo mali i golemi bukvi)
    public void NoNumbers()
    {
        //trganje na cifrite od kodot
        for (int i=0; i<n; i++)
            if ((N[i] >= 48) && (N[i] <= 57))
                if (N[i]%2 == 0)
                    N[i] = N[i] + 32;
                else
                    N[i] = N[i] + 50;
        
        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];    
    }
    
    //bez bukvi
    public void NoLetters()
    {
        //bez bukvi
        for (int i=0; i<n; i++)
        {
            if (((N[i] >= 97) && (N[i] <= 122)) || ((N[i] >= 65) && (N[i] <= 90)))
            {
                if ((N[i] >= 97) && (N[i] <= 122))
                {
                    //bez a-d
                    if ((N[i] >= 97) && (N[i] <= 100))
                        N[i] = N[i] - 62;
                    
                    //bez e-n
                    if ((N[i] >= 101) && (N[i] <= 110))
                        N[i] = N[i] - 53;
                    
                    //bez m-s
                    if ((N[i] >= 111) && (N[i] <= 115))
                    {
                        if (N[i] == 111)
                            N[i] = 33;
                        if (N[i] == 112)
                            N[i] = 42;
                        if (N[i] == 113)
                            N[i] = 64;
                        if (N[i] == 114)
                            N[i] = 95;
                        if (N[i] == 115)
                            N[i] = 94;
                    }
                    
                    //bez t-z
                    if ((N[i] >=116) && (N[i] <= 122))
                        N[i] = N[i] - 67;
                }
                
                if ((N[i] >= 65) && (N[i] <= 90))
                {
                    //bez A-D
                    if ((N[i] >= 65) && (N[i] <= 68))
                        N[i] = N[i] - 20;
                    
                    //bez E-N
                    if ((N[i] >= 69) && (N[i] <= 78))
                        N[i] = N[i] - 21;
                    
                    //bez M-S
                    if ((N[i] >= 79) && (N[i] <= 83))
                    {
                        if (N[i] == 79)
                            N[i] = 33;
                        if (N[i] == 80)
                            N[i] = 42;
                        if (N[i] == 81)
                            N[i] = 64;
                        if (N[i] == 82)
                            N[i] = 95;
                        if (N[i] == 83)
                            N[i] = 94;
                    }
                    
                    //bez T-Z
                    if ((N[i] >= 84) && (N[i] <= 90))
                        N[i] = N[i] - 33;
                }            
            }
        }
        
        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    /*bez mali bukvi i bez specialni znaci
      (so golemi bukvi i broevi)
     */
    public void CapitalLetters_Digits()
    {
        //bez specialni karakteri
        NoCharacters();

        //bez mali bukvi
        NoSmallLetters();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    /*bez mali bukvi i bez cifri
      (so golemi bukvi i specialni znaci)
     */
    public void CapitalLetters_SpecialCharacters()
    {
        //bez cifri
        NoNumbers();

        //bez mali bukvi
        NoSmallLetters();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];    
    }

    /*bez golemi bukvi i bez specialni znaci
     * so mali bukvi i broevi
     */
    public void SmallLetters_Digits()
    {
        //bez specialni znaci (gi pretvora vo bukvi i cifri)
        NoCharacters();

        //bez golemi bukvi (gi pretvora vo mali)
        NoCapitalLetters();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    /*bez golemi bukvi i bez broevi
     (so mali bukvi i specialni znaci)
     */
    public void SmallLetters_SpecialCharacters()
    {
        //bez cifri
        NoNumbers();

        //golemite bukvi gi pretvora vo mali
        NoCapitalLetters();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    /* bez specialni znaci i bez broevi
      (so mali i golemi bukvi)
     */
    public void OnlyLetters()
    {
        //bez specialni znaci (gi pretvora vo bukvi i cifri)
        NoCharacters();

        //bez cifri (gi pretvora vo mali i golemi bukvi)
        NoNumbers();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    //samo so mali bukvi
    public void SmallLetters()
    {
        //bez specialni znaci (gi pretvora vo bukvi i cifri)
        NoCharacters();

        //bez cifri(Gi pretvora vo mali i golemi bukvi)
        NoNumbers();

        //bez golemi bukvi (gi pretvora golemite vo mali bukvi)
        NoCapitalLetters();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    //samo so golemi bukvi
    public void CapitalLetters()
    {
        //bez specialni znaci (gi pretvora vo bukvi i cifri)
        NoCharacters();

        //bez cifri(Gi pretvora vo mali i golemi bukvi)
        NoNumbers();

        //bez mali bukvi (Gi pretvora vo golemi bukvi)
        NoSmallLetters();

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    // samo so specialni znaci
    public void SpecialCharacters()
    {
        //gi pretvora vo cifri
        OnlyDigits();

        //cifrite gi pretvora vo specialni znaci
        for (int i=0; i<n; i++)
        {
            if (N[i] == 48)
                N[i] = 94;
            if (N[i] == 49)
                N[i] = 95;
            if (N[i] == 50)
                N[i] = 64;
            if (N[i] == 51)
                N[i] = 42;
            if (N[i] == 52)
                N[i] = 38;
            if (N[i] == 53)
                N[i] = 35;
            if (N[i] == 54)
                N[i] = 37;
            if (N[i] == 55)
                N[i] = 36;
            if (N[i] == 56)
                N[i] = 33;
            if (N[i] == 57)
                N[i] = 35;
        }

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }

    //samo so cifri
    public void OnlyDigits()
    {
        //gi pretvora broevite od nizata N vo ednocifreni broevi
        for (int i=0; i<n; i++)
            N[i] = N[i] % 10;

        //gi pretvora vo ASCII kodovite na cifrite
        for (int i=0; i<n; i++)
            N[i] = N[i] + 48;

        //pretvoranje vo znakovi karakteri
        for (int i=0; i<n; i++)
            code [i] = (char) N[i];
    }
    public char [] getCode()
    {
        return code;
    }

    public int [] getRandom()
    {
        return N;
    }

    public int getNumber()
    {
        return n;
    }

    public String stringCode()
    {
        String str = new String(code);
        return str;
    }
}
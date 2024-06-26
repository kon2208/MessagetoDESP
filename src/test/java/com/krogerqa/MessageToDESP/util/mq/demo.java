package com.krogerqa.MessageToDESP.util.mq;

public class demo
{
    public String readstring()
    {
    String name ="Ranjan Kumar";
        return name;
    }

    public String Readtxt()
    {
        String str ="<NS1:SigRoute xmlns:NS1=\"http://webservices.sig.kroger.com/ws/SigRoute/namespace\">\n" +
                "\t<NS1:Header>\n" +
                "\t\t<NS1:MessageInfo NS1:DateTime=\"2023-03-03T07:13:33-05:00\" NS1:CorrelationId=\"d106c908-b9bc-11ed-ab9e-0aa444050006\" NS1:MessageId=\"d11dbcbc-b9bc-11ed-a838-0aa444050000\" NS1:Type=\"StoreEvent\" NS1:Event=\"/RETAIL/DATA/POS/KLOG/00\"/>\n" +
                "\t\t<NS1:SourceInfo NS1:Type=\"Store\" NS1:Id=\"00341\" NS1:Division=\"016\" NS1:SourceSystem=\"POS\" NS1:SourceApp=\"ACE\"/>\n" +
                "\t\t<NS1:ServiceInfo NS1:ServiceName=\"StoreServices\" NS1:ServiceVersion=\"23.1.0.0002\" NS1:ComponentName=\"StoreKlogProcessor\" NS1:ComponentVersion=\"21.4.0.0001\" NS1:Host=\"StoreKlogProcessor\"/>\n" +
                "\t</NS1:Header>\n" +
                "\t<NS1:Status NS1:State=\"ok\"/>\n" +
                "\t<NS1:RawPayload NS1:Content-Type=\"application/octet-stream\" NS1:Type=\"TLOGRAW\" NS1:Encoding=\"base64\" NS1:Compression=\"gzip\" NS1:Version=\"1.0\">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</NS1:RawPayload>\n" +
                "\t<NS1:Payload NS1:Content-Type=\"application/xml\" NS1:Encoding=\"base64\" NS1:Compression=\"gzip\" NS1:Type=\"KLOG\" NS1:Version=\"21.4.0.0001\">H4sIAAAAAAAAAO1bbXPaOBD+Kx5/vLlgyS/Yzhh3KJCWNoRcgLveRweURFdjU9luw/36W9mG2CAI\n" +
                "HLQljUmGxKvdR9LuanclkPPmcepLXwmLaBg0ZFxDskSCcTihwX1DHg0vzixZimIvmHh+GJCGPCeR\n" +
                "/MZ1Pl6G91LP+ydkfy5k67LUo0GZcEEfl49YlqCvIGrID3E8O1eUb9++1QJ2d+axOKqF7F7pfroh\n" +
                "sUd9JfCmJJp5Y6LIrgQvZ8i8IPLGMQBJw/mMtMIJjAWhrHmVZRAmbEyas5lPxx4nLERcct0fOMpO\n" +
                "rEvgt0lEAxJFo4DGS2rawinddmFA2fAHcciI7CKsI2TrhqNkfE+IihDS+Stkn0HVfBTAjfU6cpQy\n" +
                "bck6IF8SMBO5Sqa3hLkadpQV0pIVZgy2ansxGdIpcVWk6mdIPVPxEOvn2DhXtTNknCPoa4VzidCf\n" +
                "EebBnGAA7f5I02zTUQq0Jd87FkbRdRjRmH4lrm3VbJvDlsll7ity76XkBd+SsOS7IWNCZ/G2CTjK\n" +
                "KtPT2NmEsIvEv6O+PyVBXLB02ZaFhivwPrcFT58vaRQ7ympTSS7tINc5VnVkqHUbYU21TfhBYJci\n" +
                "w5MH7DIupxfCqqPxnDuYe91tfRxdO0qJuGQtQPVI/BBO3Nbo5u2g2+44ynpbQbncYQvroTy5S/DS\n" +
                "bkymJWrmgJ5P1qjZoAkbP0C8oBF5T8FJ4GkuXZKvxG/IbTKDxc4HIrs6mE3EK0YF3+xOQA4mLvH/\n" +
                "23z65QgglIHBg4sieFkqf0e2kXl63iIUVAq9iTm4dEGtWRQprHhxuxCKx4DMDNeMjonUShiDdTyH\n" +
                "6Dtoyy7G+TJa4RODdR5jEkzIpDkNkyAWYjlKmUkM9EfiZcrm3fbvesSLEpZHuU4ToBxlwSIG4A7S\n" +
                "BcuOeZS48L37zXYaeo/erU+abswSAtF58ShkVnYD5qBbe2yTaMwgZHCf77+XBsPmsCMNm5/S/ouN\n" +
                "20A+JIxGMJRF4nAHEKqzKaw17YqThpj++25/DWYt+Ai1mFkenrrB2E/AzN2g1MRV1pC5qmWBf5g6\n" +
                "97WSwOYur8Iy9CoeqvEV5yirfJsRN+HULY6zzWGVTQbf1xNa/dHV8O/DXaHFxzo/gi+8HQ0vOzeV\n" +
                "NxRw7P/vDWnwvAzHn8lEWFLY5xidYx1ygIBxvR9hHlwtzzZXZxmIMM1+1+SL8U9IvqaqWUaVfF9B\n" +
                "8i2oCspVegfuJUE8nGXhryGb2hbP2WvxlCRzxTfT+NiQB8ltzKCsXY9seSR6Lv5cs3Aa5lsvhOpp\n" +
                "/ajamoF5dHhq2ij/XVZjrmAvCoN0n5rplW+BlqQtYrOQxbC1T9kQFyoShDKKwJhVybWAOTjJoppZ\n" +
                "P8kkW1erkqvyhiXOAQV48550gphtCWfAwWsstxcG0gcvkBD+XcK2znvMWzaKpsj5sYYG6bXwuFFm\n" +
                "8ABBL4XlnZwhDL9DhM7TX0g3y2bxRLfO56cUmOrJFZg//nQHWzrsC/T6iyowVf14BaZq1DjSqygw\n" +
                "q1Jj/+QCvqafYnLBNR1XpUblDQscfECp8TOSr1YlXwPedNVWX9jpjnbE0x2tOt05kdMd/YDTHcOw\n" +
                "sHrk0529VmOu4Op055coueyaaZ1kjjXqVcVVOcMCx3xZBZf+2guuOrZt3eTm115UwaXVj1dwpViv\n" +
                "pOCqUu/+0Rb8Q8UnGG7Vg47Sq9z7a3kDqqkvLPkaJ5d8Nf0IyVff/bQj+xqAhpF1Wsk32ypeeFPq\n" +
                "z3PTWLaauqmgSQjxTP7Wa7ZxlOSdAf2Q3H3yhxVcE/sdVeDcB4//RZS9llKu3tM6qtgxitVPLorZ\n" +
                "1hGimGnsGMWs1IEwbCLwMaKYIL7sEXV6iR9TblYw/zsGQk8Qm5rEE8z48k//UZb2nghCmec+n83z\n" +
                "/DE+ns2hftiOZQeQItvBW59UMzewqigjk3z/U6ZtF+0EMVmRXJA2CqbK5NfLOo80iqNcdpUqFN11\n" +
                "8zUgjHp+7nemyQNHgSCGfTb+mEeOP0PuUkzK/uQVjSqOBRsq0fS61vZCtJnAOmL03/XbU5vZ0nBu\n" +
                "WCbi127WW4QAyvMdOS1wCxq3yS3dmjbp1GPz5njMJ5Ur+rfCS1WRkbraOpt4ZNu6hUI9Vf+zxj/2\n" +
                "18Fhf8B3RZnZr30vaMqFO4ncH/fyhHoN6Vv2JOL9yJHmGM49P15YYr2TVhLF4ZTwW4e6hbGJdEvX\n" +
                "wWsL9JVuxICLWmL14lvpaqjrKPyWq/sf4XTjySM7AAA=\n" +
                "</NS1:Payload>\n" +
                "\t<NS1:UserFields>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.POSLogDateTime\">2023-03-03T07:13:00-05:00</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.WorkstationID\">0505</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.SequenceNumber\">9</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.TransactionType\">00</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.OrderNumber\">N/A</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.OnlinePayments\">false</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.EbtOnline\">false</NS1:UserField>\n" +
                "\t\t<NS1:UserField NS1:Name=\"KLog.Pharmacy\">false</NS1:UserField>\n" +
                "\t</NS1:UserFields>\n" +
                "</NS1:SigRoute>";

        return str;
    }



}

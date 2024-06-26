Feature: Reusable scenarios
  Background:
    * configure ssl = true
    * configure charset = null

  @ignore
  Scenario: write reusable functions
    * def randomEmilId =
    """
    function(){return "ec_groups_karate" + System.nanoTime() + "@test.com";}
    """

    * def correlationId =
    """
    function(startwith) { return startwith + "_karate_" + (Math.floor(Math.random() * 1000000) + 1);}
    """

    * def idempotencyKey =
    """
    function() { return java.util.UUID.randomUUID().toString(); }
    """

    * def generateGUID =
    """
    function() { return java.util.UUID.randomUUID().toString(); }
    """

    * def randomCardNumber =
    """
    function() {
    const randomNum1 = Math.floor(Math.random() * (999 - 101) + 101);
    const randomNum2 = Math.floor(Math.random() * (999 - 101) + 101);
    const randomNum3 = Math.floor(Math.random() * (999 - 101) + 101);
    const randomNum4 = Math.floor(Math.random() * (999 - 101) + 101);
    const card = "48" + randomNum1 + randomNum2 + randomNum3 + randomNum4;
    return card;
    }
    """

    * def generateEHHN =
    """
    function() {
    const randomNum1 = Math.floor(Math.random() * (99999 - 1001) + 1001);
    const randomNum2 = Math.floor(Math.random() * (99999 - 1001) + 1001);
    const number = randomNum1 + randomNum2;
    return "" + number + "";
    }
    """

    * def upperCaseGUID =
      """
      function(guid) {
       return guid = guid.toUpperCase();
        }
      """

    * def inputReqHeaders =
      """
      function(inputHeaderCard,cardNumber,personID,groupId,ehhNo,householdId) {
        if (inputHeaderCard == 'N') {
          return {
            'Kroger-Person-LoyaltyCard': cardNumber
          };
        } else if (inputHeaderCard == 'Y') {
          return {
            'Kroger-Person-Id': personID
          };
        } else if (inputHeaderCard == 'B') {
          return {
            'Kroger-Person-Id': personID,
            'Kroger-Person-LoyaltyCard': cardNumber
          };
        }
         else if (inputHeaderCard == 'G') {
          return {
            'Kroger-Group-Id': groupId
          };

      }
        else if (inputHeaderCard == 'E') {
          return {
            'Kroger-Alias-Key': 'ehhn',
            'Kroger-Alias-Value':ehhNo
          };

      }
        else if (inputHeaderCard == 'H') {
          return {
          'Kroger-Alias-Key': 'householdId',
           'Kroger-Alias-Value':householdId
          };

      }
      }
      """

    * def getReqHeaders =
      """
      function(inputHeaderCard,cardNumber,personID,TestCase) {
        if (inputHeaderCard == 'N') {
          return {
              'Kroger-Person-LoyaltyCard': cardNumber
          };
        } else {
          return {
            'Kroger-Person-Id': personID
          };
        }
        }
      """

    * def constructHeaders =
      """
      function(KrogerTenantHeader,TenantValue,CorrelationidHeader,correlationidValue,authheader,authvalue,authentication) {
      let auth;
      if(authheader == "N")
      { auth = authvalue;}
      else
      { auth = authentication;}

        if (KrogerTenantHeader == 'N' && CorrelationidHeader == 'N') {
          return {
            'accept': 'application/json',
            'Authorization': auth
          };
        } else if (KrogerTenantHeader == 'N'){
          return {
            'accept': 'application/json',
            'X-Correlation-ID' : correlationidValue,
            'Authorization': auth
          };
        } else if (CorrelationidHeader == 'N') {
        return {
            'accept': 'application/json',
             'X-Kroger-Tenant' : TenantValue,
             'Authorization': auth
          };
        }
        else {
        return {
            'accept': 'application/json',
            'X-Correlation-ID' : correlationidValue,
            'X-Kroger-Tenant' : TenantValue,
            'Authorization': auth
            };
          }
        }
      """

    * def constructPostHeaders =

      """
       function(KrogerTenantHeader,TenantValue,CorrelationidHeader,correlationidValue,authheader,authvalue,authentication,ContentTypeHeader,contentTypeValue,IdempotencyKeyHeader) {
       let auth;
       if ( authheader == 'N' )
       { auth = authvalue;}

       else{ auth = authentication; }

       if( KrogerTenantHeader == 'N' && CorrelationidHeader == 'N' && IdempotencyKeyHeader == 'N' && ContentTypeHeader == 'N' && authheader == 'M') {
           return { 'accept': 'application/json'};
            }
            else if ( KrogerTenantHeader == 'N' && CorrelationidHeader == 'N' ) {
            return {
            'Idempotency-Key':idempotencyKey(),
            'Content-Type': contentTypeValue,
            'Authorization': auth
            };
            }
            else if ( KrogerTenantHeader == 'N' ) {
            return {
            'X-Correlation-ID' : correlationidValue,
            'Idempotency-Key':idempotencyKey(),
            'Content-Type': contentTypeValue,
            'Authorization': auth
            };
            }
            else if ( CorrelationidHeader == 'N' ) {
            return {
            'X-Kroger-Tenant' : TenantValue,
            'Idempotency-Key':idempotencyKey(),
            'Content-Type': contentTypeValue,
            'Authorization': auth
            };
            }
            else if ( IdempotencyKeyHeader == 'N' ) {
            return {
            'X-Correlation-ID' : correlationidValue,
            'X-Kroger-Tenant' : TenantValue,
            'Content-Type': contentTypeValue,
            'Authorization': auth
            };
            }
            else if ( ContentTypeHeader == 'N' ) {
            return {
            'X-Correlation-ID' : correlationidValue,
            'X-Kroger-Tenant' : TenantValue,
            'Idempotency-Key':idempotencyKey(),
            'Authorization': auth
            };
            }
            else if ( authheader == 'M' ) {
            return {
            'X-Correlation-ID' : correlationidValue,
            'X-Kroger-Tenant' : TenantValue,
            'Idempotency-Key':idempotencyKey(),
            'Content-Type': contentTypeValue
            };
            }
            else {
            return {
            'X-Correlation-ID' : correlationidValue,
            'X-Kroger-Tenant' : TenantValue,
            'Content-Type': contentTypeValue,
            'Idempotency-Key':idempotencyKey(),
            'Authorization': auth,
            }
            }
            }
      """


    * def constructPatchHeaders =
      """
      function(KrogerTenantHeader,TenantValue,CorrelationidHeader,correlationidValue,authheader,authvalue,authentication,content_type_header,content_type_value) {
      let auth;
      if(authheader == "N")
      { auth = authvalue;}
      else
      { auth = authentication;}

        if (KrogerTenantHeader == 'N' && CorrelationidHeader == 'N') {
          return {
            'accept': 'application/json',
            'Authorization': auth,
            'Content-Type' : content_type_value
          };
        } else if (KrogerTenantHeader == 'N'){
          return {
            'accept': 'application/json',
            'X-Correlation-ID' : correlationidValue,
            'Authorization': auth,
            'Content-Type' : content_type_value
          };
        } else if (CorrelationidHeader == 'N') {
        return {
            'accept': 'application/json',
             'X-Kroger-Tenant' : TenantValue,
             'Authorization': auth,
             'Content-Type' : content_type_value
          };
        } else if (content_type_header == 'N') {
        return {
            'accept': 'application/json',
            'X-Correlation-ID' : correlationidValue,
             'X-Kroger-Tenant' : TenantValue,
             'Authorization': auth
          };
        } else {
        return {
            'accept': 'application/json',
            'X-Correlation-ID' : correlationidValue,
            'X-Kroger-Tenant' : TenantValue,
            'Authorization': auth,
            'Content-Type' : content_type_value
            };
          }
        }
      """

    * def chooseGroupId =
    """
    function(groupidHeader,groupIdValue,groupIdFromTestcase){
    let id;
     if ( groupidHeader == "Y")
     {
     id = groupIdValue ;
     }
     else {
     id = groupIdFromTestcase;
     }
     return id;
     }
    """

    * def chooseVersionKey =
    """
    function(VersionkeyHeader,versionKeyValue,actualVersionKey){
    let key;
     if ( VersionkeyHeader == "Y")
     {
     key = versionKeyValue ;
     } else {
     key = actualVersionKey;
     }
     return key;
     }
    """

Feature: Rest API Helpers

  Background:
    * configure ssl = true
    * def authToken = karate.properties['authValue']

  @ignore @createGroup
  Scenario: Create a new Group for given Input request
    * def header =
    """
    {
    'Authorization' : "#(authToken)"
    }
    """
    * print '\n\n******************** Hitting Groups POST API ******************************' + '\nURL : ' + baseURL + groupsGetEndpoint
    * configure headers = {'Content-Type' :  'application/json', 'Idempotency-Key' : #(idempotencyKey()),'X-Correlation-ID' : #(correlationId("groupsPatch")), 'X-Kroger-Tenant' : 'kroger' }
    * def postBody = read ('classpath:data/Input/GroupsPatch/' + createGroup)
    Given url baseURL
    And path groupsGetEndpoint
    And headers header
    Then request postBody
    When method post
    Then status 201
    Then karate.log('\nActual Response :\n', response)
    * def group_id = response.data.id
    * def versionKey = response.data.versionKey

  @ignore @deleteGroup
  Scenario: Delete Group with given Group ID
    * def header =
    """
    {
    'Authorization' : "#(authToken)"
    }
    """
    * print '\n\n******************** Hitting Groups DELETE API ******************************' + '\nURL : ' + baseURL + groupsGetEndpoint
    * configure headers = {'X-Correlation-ID' : #(corrID), 'X-Kroger-Tenant' : 'kroger', 'Kroger-Group-Id': #(groupid) }
    Given url baseURL
    And path groupsGetEndpoint
    And headers header
    When method delete
    Then status 204
    Then karate.log('\nActual Response :\n', response)

  @ignore @checkGroupExistsAndDelete
  Scenario: check Group Existence And Delete
    * call read('classpath:com/kroger/ec/platform/utils/Helpers.feature')
    * def getHeaders = {'X-Correlation-ID': #(corrID), 'X-Kroger-Tenant': 'kroger', 'Authorization': #(authToken)}
    * configure headers = inputheader
    * def queryParam = {filter.type : 'HOUSEHOLD'}
    #* print 'getHeaders : ', getHeaders

    # checking group exists or not with card
    Given url baseURL
    And path groupsGetEndpoint
    And params queryParam
    And headers getHeaders
    When method get
    #Then karate.log('\nActual Response :\n', response)
    * def groupID = karate.jsonPath(response, '$.data[*].id')
    * print 'Group Exists with card is :', groupID

    * def deleteHeaders = {'X-Kroger-Tenant': 'kroger', 'X-Correlation-ID': #(corrID), 'Authorization': #(authentication), 'Kroger-Group-Id': #(groupID) }
    # deleting if groups exists
    Given url baseURL
    And path groupsGetEndpoint
    And headers deleteHeaders
    When method delete

  @ignore @createGroupWithInput
  Scenario: Create a new Group
    * def header =
    """
    {
    'Authorization' : "#(authToken)"
    }
    """
    * print '\n\n******************** Creating a Group ******************************' + '\nURL : ' + baseURL+ groupsGetEndpoint
    * configure headers = {'Content-Type' :  'application/json', 'Idempotency-Key' : #(idempotencyKey()),'X-Correlation-ID' : #(corrID), 'X-Kroger-Tenant' : 'kroger' }
    * print '\n\nPostBody : ', postBody
    Given url baseURL
    And path groupsGetEndpoint
    And headers header
    Then request postBody
    When method post
    Then status 201
    #Then karate.log('\nActual Response :\n', response)
    * def group_id = response.data.id
    * def versionKey = response.data.versionKey

  @ignore @createGroupJsonConstructor
  Scenario:
    * def createReq = read (path)
    * def members = createReq.members
    #* print 'Members :', members
    * def updatedMembers = { }
    * members.person1 ? updatedMembers[guid1]     = members.person1 : members.membersId  ? updatedMembers[idFromCsv1] = members.membersId  : members.member ? updatedMembers[memberWithSpace] = members.member :null
    * members.person2 ? updatedMembers[guid2]     = members.person2 : members.membersId2 ? updatedMembers[idFromCsv2] = members.membersId2 : null
    * members.person3 ? updatedMembers[guidLead]  = members.person3 : members.membersId3 ? updatedMembers[idFromCsv3] = members.membersId3 : members.member ? updatedMembers[memberWithSpace] = members.member :null
    * members.person4 ? updatedMembers[guidTrail] = members.person4 : members.membersId4 ? updatedMembers[idFromCsv4] = members.membersId4 : null
    * members.person5 ? updatedMembers[guid1]     = null : members.person6  ? updatedMembers[idFromCsv1]     = null : null
    * set createReq.members = updatedMembers
    * def updatedJson =  createReq
    #* print 'Updated Create Json :', updatedJson

  @ignore @expectedJsonConstructor
  Scenario:
    * def ExpMembers = ExpResp.data[0].members
    #* print 'Members :', ExpMembers
    * def updatedExpMembers = { }
    * ExpMembers.person1 ? updatedExpMembers[guid1] = ExpMembers.person1 : null
    * ExpMembers.person2 ? updatedExpMembers[guid2] = ExpMembers.person2 : null
    * set ExpResp.data[0].members = updatedExpMembers
    * def updatedExpJson =  ExpResp
    #* print 'Updated Expected Json :', updatedExpJson

  @ignore @expectedJsonConstructor2
  Scenario:
    * def ExpMembers = ExpResp.data.members
    #* print 'Members :', ExpMembers
    * def updatedExpMembers = { }
    * ExpMembers.person1 ? updatedExpMembers[guid1] = ExpMembers.person1 : ExpMembers.membersId  ? updatedExpMembers[idFromCsv1] = ExpMembers.membersId  : ExpMembers.member ? updatedExpMembers[memberWithSpace] = ExpMembers.member  : null
    * ExpMembers.person2 ? updatedExpMembers[guid2] = ExpMembers.person2 : ExpMembers.membersId2 ? updatedExpMembers[idFromCsv2] = ExpMembers.membersId2 : null
    * set ExpResp.data.members = updatedExpMembers
    * def updatedExpJson =  ExpResp
    #* print 'Updated Expected Json :', updatedExpJson

  @ignore @expectedResponseReader
  Scenario:
    * def updatedExpResp = (expResp.data.members) ? (karate.call('classpath:com/kroger/ec/platform/utils/ApiHelper.feature@expectedJsonConstructor2',{ExpResp : expResp})).updatedExpJson : expResp
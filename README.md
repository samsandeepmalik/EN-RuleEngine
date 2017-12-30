# EN-RuleEngine

    This rule engine validates streaming or batch data against multiple rules defined in rules configuration (./config/rules.yaml) file.
    Currently this rule engine validates signal data under 3 categories as below:
    - NumericActor : perform numeric data type operations as:
      - equal_to : validate signal value(Int) equal to rule value(Int).
      - greater_than : validate signal value(Int) greater than rule value(Int).
      - less_than : validate signal value(Int) less than rule value(Int).
      - greater_than_or_equal_to : validate signal value(Int) greater than or equal to rule value(Int).
      - less_than_or_equal_to : validate signal value(Int) less than or equal to rule value(Int).
    - StringActor : perform string data type operations as:
      - equal_to : compare if 2 strings(signal value and rule value) are equal.
      - starts_with : check if a signal value starts with the value defined in rule.
      - ends_with : check if signal value ends with the value defined in rule.
      - contains : check if signal value contains the value defined in rule.
      - non-empty : check if signal value is not empty.
      - matches_regex : validates signal value against regex defined in rule.
    - DateTimeActor : perform datetime data type operations as:
      - is_future : validate signal value(Date), is after or greater than current date.
      - is_future_and_equal : validate signal value(Date), is after current date or equal to rule value(Date).
      - is_past : validate signal value(Date), is before or less than current date.
      - is_past_and_equal : validate signal value(Date), is before current date or equal to rule value(Date).

# Technical Specification
    This section gives information about the technologies, third party libraries and version used in this rule engine:
    - Java-Version is 1.7
    - Scala version 2.10.6 is used as programming language
    - snakeyaml 1.19 version is used to define rules in readable format
    - Scala Test 3.0.4 version is used as Testing Framework
    - sbt version 0.13

# Conceptual Approach
    - I chose .yaml file format for defining rules because it's readable and quite easy to add/define new rules.
    - I used Scala as programming language because it has rich features and is precise. It gives you a blend
    of object-oriented and functional programming features specially immutability.
    - In this rule engine all actors and utility classes are defined as singleton objects (which prevent programmer to create
    object accidentally).
    - In RuleUtil object, rules will load from rules.yaml only once when it is referenced the first time. For implementing 
    this i used lazy evaluation feature of Scala language which will increase the performance.
    - If the Actor operation is not specified in rule config file or value is not as per value_type then it will throw error.
    - Instead of traditional if-else construct I have used pattern matching.
    - Finally if a signal violates the rule then rule engine prints the data of the signal.

# Performnace
    - Rule Execution time complexity is proportional to n (number of rules defined per signal data type) i.e. O(n).
    - Space complexity is constant O(1).
    - I didn't face any bottleneck while working on this rule engine.

# Improvement Scope
    Some of the improvemnet scopes are mentioned below (in descending priority order):
    - I would load all rules from .yaml file and put them into cache (google cache) when application is getting bootstrap 
      and would refresh that cache as per business demand like once in a day.
    - I would try to build this rule engine with different approaches like put all rules in database and write triggers when
      a rule is created or updated and refresh application rules cache.
    - I would try to make this rule engine more configurable and scalable using generic and subtyping.
    - Create GUI for the rule engine which would make it easy to manage this engine.

# Test Cases
    For testing purpose I have used ScalaTest framework with TDD approach (Test-driven development). All test cases with negative
    and positive scenarios using FunSuite (for those who worked with Junit testing framework). 
    Please find the below snapshot of Test Suite result:

    RuleEngineSpec:
    [info] - NumericActor : greater_than, Test case validate 'ALT1' with positive result
    [info] - NumericActor : Test case validate 'ALT4' signal value is not compatible with value_type
    [info] - StringActor : Test case validate 'ALT4' signal has no rule defined for given value_type
    [info] - DateTimeActor : Test case validate 'ALT3' signal rule of DateTime 'is_future' action
    [info] - DateTimeActor : Test case validate 'ALT3' signal rule of DateTime 'is_future' action with incorrect date pattern
    [info] Run completed in 237 milliseconds.
    [info] Total number of tests run: 5
    [info] Suites: completed 1, aborted 0
    [info] Tests: succeeded 5, failed 0, canceled 0, ignored 0, pending 0
    [info] All tests passed.
    [success] Total time: 2 s, completed 30-Dec-2017 12:43:46

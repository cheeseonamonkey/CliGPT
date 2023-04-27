# CliGPT
ChatGPT implementation terminal assistant — powered by OpenAI's API.


## Usage

1. #### Set API key *(make keys [***here***](https://platform.openai.com/account/api-keys)):*
 ```
  ❯ askgpt -k sk-sadfasdfasdfasdfasdfasdf
  
  ❯
  
 ```
2. #### Prompt
 ```
  ❯ askgpt 'What's up bro?'
  
   I'm doing well, thank you for asking. How can I assist you today?
  ❯
  
 ```
 

### Help (`-h`) output:
```
    Usage: AskGpt options_list
    Arguments: 
        prompt -> Prompt { String }

    Options: 
        --verbose, -v [false] -> Enable verbose output 
        ~~--tell, -t [false] -> Execute shell command~~
        --setApiKey, -k [false] -> Set API key 
        --help, -h -> Usage info 
```

### Troubleshoots:
- `chmod +x` 
- java version ?

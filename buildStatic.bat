npm config set registry http://registry.npmjs.org/
npm config set proxy http://localhost:3128
npm config set https-proxy http://localhost:3128
npm config set strict-ssl false
set HTTPS_PROXY=http://localhost:3128
set HTTP_PROXY=http://localhost:3128
export HTTPS_PROXY=http://localhost:3128
export HTTP_PROXY=http://localhost:3128
export http_proxy=http://localhost:3128

npm --proxy http://localhost:3128 \
--without-ssl --insecure install --save-dev babel-cli